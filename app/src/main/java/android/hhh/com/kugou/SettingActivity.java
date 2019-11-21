package android.hhh.com.kugou;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBackIcon;
    private LinearLayout lySettingItem_1;
    private LinearLayout lySettingItem_2;
    private LinearLayout lySettingItem_3;
    private LinearLayout lySettingItem_4;
    private SlideButton sbSetting_1;
    private SlideButton sbSetting_2;
    private SlideButton sbSetting_3;
    private SlideButton sbSetting_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        sbSetting_1.setChecked(true);
    }

    private void initView(){
        ivBackIcon = (ImageView) findViewById(R.id.iv_backIcon);
        lySettingItem_1 = (LinearLayout) findViewById(R.id.ly_setting_1);
        lySettingItem_2 = (LinearLayout) findViewById(R.id.ly_setting_2);
        lySettingItem_3 = (LinearLayout) findViewById(R.id.ly_setting_3);
        lySettingItem_4 = (LinearLayout) findViewById(R.id.ly_setting_4);
        sbSetting_1 = (SlideButton) findViewById(R.id.sb_setting_1);
        sbSetting_2 = (SlideButton) findViewById(R.id.sb_setting_2);
        sbSetting_3 = (SlideButton) findViewById(R.id.sb_setting_3);
        sbSetting_4 = (SlideButton) findViewById(R.id.sb_setting_4);
        sbSetting_1.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sb_setting_1:
                sbSetting_1.setChecked(true);
                break;
        }
    }
}
