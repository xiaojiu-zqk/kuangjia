package com.kuangjia.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kuangjia.interfaces.IBasePersenter;
import com.kuangjia.interfaces.IBaseView;

import butterknife.Unbinder;


public abstract class BaseActivity<P extends IBasePersenter> extends AppCompatActivity implements IBaseView {

    protected P persenter;
    Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        //unbinder = ButterKnife.bind(this);
        initView();
        persenter = createPersenter();
        if(persenter != null){
            persenter.attachView(this);
        }
        initData();
    }

    /**
     * 通过模板的设计模式，定义需要处理的方法
     */
    protected abstract int getLayout();
    //初始化界面的操作
    protected abstract void initView();
    //初始化数据
    protected abstract void initData();
    //创建p的方法
    protected abstract P createPersenter();

    /**
     * 用来显示提示信息
     * @param msg
     */
    @Override
    public void showTips(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * activity移除的时候解绑persenter和v层
     * 解绑当前activity的butterknife
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(persenter != null){
            persenter.detachView();
        }
        if(unbinder != null){
            unbinder.unbind();
        }
    }
}
