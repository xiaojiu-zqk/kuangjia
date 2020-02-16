package com.kuangjia.common;


import com.kuangjia.apps.MyApp;

import java.io.File;

public class Constant {




    //网络缓存的地址
    public static final String PATH_DATA = MyApp.myApp.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/shop";


}
