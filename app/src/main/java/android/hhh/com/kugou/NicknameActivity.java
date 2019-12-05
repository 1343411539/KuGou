package android.hhh.com.kugou;

import android.content.Intent;
import android.hhh.com.kugou.xiongli.utils.ToastUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NicknameActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_nickname;
    private TextView tv_amount, save;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
        initView();
        setClick();
        editTextListener();// 对EditText的文本变化进行监听
    }

    private void editTextListener() {
        et_nickname.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;
            private final int MAX_LENGTH = 12;// 定义EditText的最大输入长度

            // 文本在改变之前的状态
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            // 文本变化时的状态.
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
                Log.i("NicknameActivity", String.valueOf(s));
            }

            // 检测文本改变之后的状态
            @Override
            public void afterTextChanged(Editable s) {
                int amount = MAX_LENGTH - s.length();
                tv_amount.setText(String.valueOf(amount));

                selectionStart = et_nickname.getSelectionStart();// 获取光标当前位置
                selectionEnd = et_nickname.getSelectionEnd();
                if (temp.length() > MAX_LENGTH) {
                    s.delete(selectionStart - (temp.length() - MAX_LENGTH), selectionEnd);// 删除光标前的字符
                    int tempSelection = selectionStart;
                    et_nickname.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_nickname);
        save = (TextView) findViewById(R.id.tv_save);
        et_nickname = (EditText) findViewById(R.id.et_nickname);
        tv_amount = (TextView) findViewById(R.id.tv_amount);
    }

    private void setClick(){
        save.setOnClickListener(this);
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_save:
                //创建Intent对象，跳转到编辑资料页面
                Intent intent = new Intent(this, EditInfoActivity.class);
                //将数据存入Intent对象
                intent.putExtra("nickname", String.valueOf(et_nickname.getText()));
                startActivity(intent);
                break;
        }
    }
}
