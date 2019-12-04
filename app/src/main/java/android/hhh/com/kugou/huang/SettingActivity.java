package android.hhh.com.kugou.huang;

import android.content.Intent;
import android.hhh.com.kugou.LoginActivity;
import android.hhh.com.kugou.MainActivity;
import android.hhh.com.kugou.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

    }

    public void newLogin(View v){
        Intent newSet = new Intent(SettingActivity.this , LoginActivity.class);
        startActivity(newSet);
    }
}
