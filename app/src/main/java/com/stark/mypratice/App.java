/**
 * Copyright (C), 2007-2022, 未来穿戴有限公司
 * FileName: App
 * Author: lpq
 * Date: 2022/1/19 11:03
 * Description: 用一句话描述下
 */
package com.stark.mypratice;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 *
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice
 * @ClassName: App
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2022/1/19 11:03
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
