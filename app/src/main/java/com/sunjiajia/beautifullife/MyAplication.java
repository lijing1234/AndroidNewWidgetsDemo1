/*
 *
 *  *
 *  *  *
 *  *  *  * ===================================
 *  *  *  * Copyright (c) 2016.
 *  *  *  * 作者：安卓猴
 *  *  *  * 微博：@安卓猴
 *  *  *  * 博客：http://sunjiajia.com
 *  *  *  * Github：https://github.com/opengit
 *  *  *  *
 *  *  *  * 注意**：如果您使用或者修改该代码，请务必保留此版权信息。
 *  *  *  * ===================================
 *  *  *
 *  *  *
 *  *
 *
 */

package com.sunjiajia.beautifullife;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

import java.util.logging.Level;

/**
 * Created by lijing on 2016/12/8.
 */

public class MyAplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        HttpHeaders headers=new HttpHeaders();
        headers.put("","");
        headers.put("","");
        HttpParams params=new HttpParams();
        params.put("","");
        OkGo.init(this);


        try {
            OkGo.getInstance()
                    .debug("OKGo", Level.INFO,true)
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)
                    .setCacheMode(CacheMode.NO_CACHE)
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                    .setRetryCount(3)
                    .setCookieStore(new PersistentCookieStore())
                    .setCertificates()
                    .addCommonHeaders(headers)
                    .addCommonParams(params);

        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
