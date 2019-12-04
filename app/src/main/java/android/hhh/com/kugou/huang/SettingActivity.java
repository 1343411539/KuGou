package android.hhh.com.kugou.huang;

import android.content.Intent;
import android.hhh.com.kugou.LoginActivity;
import android.hhh.com.kugou.MainActivity;
import android.hhh.com.kugou.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBackIcon;
    private TextView tvBackIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    private void initView(){
        ivBackIcon = (ImageView) findViewById(R.id.iv_backIcon);
        tvBackIcon = (TextView) findViewById(R.id.tv_backIcon);

        ivBackIcon.setOnClickListener(this);
        tvBackIcon.setOnClickListener(this);
    }

    public void newLogin(View v){
        Intent newSet = new Intent(SettingActivity.this , LoginActivity.class);
        startActivity(newSet);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
