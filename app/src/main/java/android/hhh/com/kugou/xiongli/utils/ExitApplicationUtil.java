package android.hhh.com.kugou.xiongli.utils;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vip98 on 2019/12/3.
 * 定义一个类，用于实现退出app.
 * <p>
 * 在每个Activity的onCreate方法里面通过ExitApplicationUtil.getInstance().addActivity(this),
 * 添加当前Activity到mList里面去;
 * <p>
 * 最后在想退出的时候调用SysApplication.getInstance().exit(),可直接关闭所有的Activity并退出应用程序
 */


public class ExitApplicationUtil extends Application {
    private List<Activity> mList = new LinkedList<Activity>();
    private static ExitApplicationUtil instance;

    public synchronized static ExitApplicationUtil getInstance() {
        if (null == instance) {
            instance = new ExitApplicationUtil();
        }
        return instance;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    // exitApp
    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null) activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
