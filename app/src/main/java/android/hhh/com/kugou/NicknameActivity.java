package android.hhh.com.kugou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class NicknameActivity extends AppCompatActivity {

    private EditText et_nickname;
    private TextView tv_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
        initView();
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
                    s.delete(selectionStart - (temp.length() - MAX_LENGTH), selectionEnd);// 删除光标前字符
                    int tempSelection = selectionStart;
                    et_nickname.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_profession);
        et_nickname = (EditText) findViewById(R.id.et_nickname);
        tv_amount = (TextView) findViewById(R.id.tv_amount);
    }
}
