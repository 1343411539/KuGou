package android.hhh.com.kugou;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etName = (EditText)findViewById(R.id.et_name);
        etPassword = (EditText)findViewById(R.id.et_password);

        initView();
    }

    private void initView(){;
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog(){
        AlertDialog builder = new AlertDialog.Builder(this)
                .setTitle("温馨提示")
                .setMessage("是否要保存账户信息？")
                .setNegativeButton("确定",
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //创建Intent对象，启动LoginActivity
                                Intent intend = new Intent(LoginActivity.this, MainActivity.class);
                                //将数据存入Intent对象中
                                intend.putExtra("name", etName.getText().toString().trim());
                                intend.putExtra("password", etPassword.getText().toString().trim());
                                startActivity(intend);
                            }
                        })
                .setPositiveButton("取消",
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //创建Intent对象，启动LoginActivity
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                .show();
    }
}
