package android.hhh.com.kugou.huang;

import android.content.Intent;
import android.hhh.com.kugou.LoginActivity;
import android.hhh.com.kugou.MainActivity;
import android.hhh.com.kugou.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout lySettingItem10;
    LinearLayout lySettingItem11;
    private ImageView ivBackIcon;
    private TextView tvBackIcon;
    private Intent intent;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        initView();

        intent = getIntent();
        name = intent.getStringExtra("name");
        if(name==null || name==""){
            lySettingItem11.setVisibility(View.GONE);
        }else {
            lySettingItem10.setVisibility(View.GONE);
        }
    }

    private void initView() {
        lySettingItem10 = (LinearLayout) findViewById(R.id.ly_setting_item_10);
        lySettingItem11 = (LinearLayout) findViewById(R.id.ly_setting_item_11);
        ivBackIcon = (ImageView) findViewById(R.id.iv_backIcon);
        tvBackIcon = (TextView) findViewById(R.id.tv_backIcon);

        ivBackIcon.setOnClickListener(this);
        tvBackIcon.setOnClickListener(this);
    }

    public void newLogin(View v) {
        Intent newSet = new Intent(SettingActivity.this, LoginActivity.class);
        startActivity(newSet);
    }

    public void newEnd(View v) {
        Intent newSet = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(newSet);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent newSet = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(newSet);
    }
}
