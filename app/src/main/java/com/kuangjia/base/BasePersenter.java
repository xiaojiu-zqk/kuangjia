package com.kuangjia.base;


import com.kuangjia.interfaces.IBasePersenter;
import com.kuangjia.interfaces.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class BasePersenter<V extends IBaseView> implements IBasePersenter<V> {

    protected V mView;
    //对V层的进行弱引用
    WeakReference<V> weakReference;

    //rxjava2 背压式网络请求处理
    CompositeDisposable compositeDisposable;

    @Override
    public void attachView(V view) {
        weakReference = new WeakReference<>(view);
        mView = weakReference.get();
    }

    /**
     * 把当前业务下的网络请求对象添加到compositedisposable
     * @param disposable
     */
    public void addSubscribe(Disposable disposable){
        if(compositeDisposable == null) compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable);
    }

    /**
     * 在界面关闭时候移除网络请求对象
     */
    public void unSubscribe(){
        compositeDisposable.clear();
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
