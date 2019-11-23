package android.hhh.com.kugou.xiongli.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by vip98 on 2019/11/22.
 */

public class DialogUtils extends Dialog {
    private Context context;

    public DialogUtils(Context context) {
        super(context);
        this.context = context;
    }

    /**
     * 获取屏幕大小的两种常用方法：
     * 1.DisplayMetrics metrics= new DisplayMetrics();
     * getWindowManager().getDefaultDisplay().getMetrics(metrics);
     * <p>
     * 2.DisplayMetric metrics=mContext.getResources().getDisplayMetric();
     * <p>
     * 设置dialog位于屏幕底部
     */
    private void setViewLocation() {
        DisplayMetrics metrics = new DisplayMetrics();
        /*activity.getWindowManager().getDefaultDisplay().getMetrics(dm);*/
        metrics = context.getResources().getDisplayMetrics();
        int height = metrics.heightPixels;// 表示屏幕的像素高度，单位是px（像素）
        //获得当前窗体
        Window window = this.getWindow();
        //获取窗体属性
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;
        lp.y = height;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        onWindowAttributesChanged(lp);
    }
}
