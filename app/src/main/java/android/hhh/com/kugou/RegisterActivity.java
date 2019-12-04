package android.hhh.com.kugou;

import android.content.Intent;
import android.hhh.com.kugou.huang.Code;
import android.hhh.com.kugou.huang.DBOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivRegisterShowCode;
    EditText etRegisterUsername;
    EditText etRegisterPassword;
    EditText etRegisterPasswordEnter;
    EditText etRegisterPhoneCodes;
    Button btRegisterRegister;
    private String realCode;
    private DBOpenHelper mDBOpenHelper;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initView();
        mDBOpenHelper = new DBOpenHelper(this);

        ivRegisterShowCode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView(){
        ivRegisterShowCode = (ImageView) findViewById(R.id.iv_registeractivity_showCode);
        etRegisterUsername = (EditText) findViewById(R.id.et_registeractivity_username);
        etRegisterPassword = (EditText) findViewById(R.id.et_registeractivity_password);
        etRegisterPasswordEnter = (EditText) findViewById(R.id.et_registeractivity_password_enter);
        btRegisterRegister = (Button) findViewById(R.id.bt_registeractivity_register);
        ivRegisterShowCode.setOnClickListener(this);
        btRegisterRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_registeractivity_back: //返回登录页面
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.iv_registeractivity_showCode:    //改变随机验证码的生成
                ivRegisterShowCode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                break;
            case R.id.bt_registeractivity_register:    //注册按钮
                //获取用户输入的用户名、密码、验证码
                String username = etRegisterUsername.getText().toString().trim();
                String password = etRegisterPassword.getText().toString().trim();
                String phoneCode = etRegisterPasswordEnter.getText().toString().toLowerCase();
                //注册验证
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phoneCode)) {
                    if (phoneCode.equals(realCode)) {
                        //将用户名和密码加入到数据库中
                        mDBOpenHelper.add(username, password);
                        Intent intent2 = new Intent(this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                        Toast.makeText(this, "验证通过，注册成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "验证码错误,注册失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "未完善信息，注册失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Register Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

