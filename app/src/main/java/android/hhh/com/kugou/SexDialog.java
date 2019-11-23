package android.hhh.com.kugou;

import android.app.Dialog;
import android.content.Context;
import android.hhh.com.kugou.xiongli.utils.DialogUtils;
import android.hhh.com.kugou.xiongli.utils.ToastUtils;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by vip98 on 2019/11/22.
 */

public class SexDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private LinearLayout woman, man, confidentiality, cancel;
    private DialogUtils dialogUtils;

    public SexDialog(Context context) {
        super(context, R.style.MyDialogTheme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sex);
        initView();
        setOnclick();
//        dialogUtils = new DialogUtils(context);
        setViewLocation();
        //设置点击Dialog外部可以关闭Dialog
        setCanceledOnTouchOutside(true);
    }

    private void setViewLocation() {
        DisplayMetrics dm = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        dm=context.getResources().getDisplayMetrics();
        int height = dm.heightPixels;// 表示屏幕的像素高度，单位是px（像素）
        Window window = this.getWindow();
        ////获得当前窗体
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;
        lp.y = height;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        onWindowAttributesChanged(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_sex_woman:
                ToastUtils.showShort(context, "女");
                this.cancel();
                break;
            case R.id.ll_sex_man:
                ToastUtils.showShort(context, "男");
                this.cancel();
                break;
            case R.id.ll_sex_confidentiality:
                ToastUtils.showShort(context, "保密");
                this.cancel();
                break;
            case R.id.ll_sex_cancel:
                this.cancel();
                break;

        }
    }

    private void initView(){
        woman = findViewById(R.id.ll_sex_woman);
        man = findViewById(R.id.ll_sex_man);
        confidentiality = findViewById(R.id.ll_sex_confidentiality);
        cancel = findViewById(R.id.ll_sex_cancel);
    }

    private void setOnclick(){
        woman.setOnClickListener(this);
        man.setOnClickListener(this);
        confidentiality.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }
}
