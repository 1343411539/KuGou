package android.hhh.com.kugou;

import android.content.Intent;
import android.hhh.com.kugou.xiongli.utils.CopyContentUtil;
import android.hhh.com.kugou.xiongli.utils.ToastUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EditInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout portrait, nickname, sex, district, birthday, profession, signature,
            hobbies, account, id, age, certification;
    private Intent intent;
    private Toolbar navigationIcon;
    private SexDialog dialog;
    private TextView tv_account, tv_account_text, tv_id, tv_id_text;
    private CopyContentUtil copy;
    private TextView tv_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        initView();
        setOnclick();

        //获得Intent对象
        Intent intent = getIntent();
        //取出key对应的value值
        String nickname = intent.getStringExtra("nickname");
        tv_nickname.setText(nickname);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_edit_info:
                finish();
                break;
            case R.id.ll_head_portrait:
                ToastUtils.showShort(EditInfoActivity.this, "点击头像");
                break;
            case R.id.ll_nickname:
//                ToastUtils.showShort(EditInfoActivity.this, "点击昵称");
                intent = new Intent(this, NicknameActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_sex:
//                ToastUtils.showShort(EditInfoActivity.this, "点击性别");
                dialog = new SexDialog(EditInfoActivity.this);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);
                dialog.show();
                break;
            case R.id.ll_district:
                ToastUtils.showShort(EditInfoActivity.this, "点击地区");
                break;
            case R.id.ll_birthday:
                ToastUtils.showShort(EditInfoActivity.this, "点击生日");
                break;
            case R.id.ll_profession:
//                ToastUtils.showShort(EditInfoActivity.this, "点击职业");
                intent = new Intent(this, ProfessionActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_signature:
                ToastUtils.showShort(EditInfoActivity.this, "点击个性签名");
                break;
            case R.id.ll_hobbies_interests:
                ToastUtils.showShort(EditInfoActivity.this, "点击兴趣爱好");
                break;
            case R.id.ll_account:
//                ToastUtils.showShort(EditInfoActivity.this, "点击账号");
                //传入需要复制的文字的控件
                copy = new CopyContentUtil(getApplicationContext(), tv_account, tv_account_text);
                copy.init();
                break;
            case R.id.ll_kuGou_id:
                ToastUtils.showShort(EditInfoActivity.this, "点击酷狗ID");
                copy = new CopyContentUtil(getApplicationContext(), tv_id, tv_id_text);
                copy.init();
                break;
            case R.id.ll_age:
                ToastUtils.showShort(EditInfoActivity.this, "点击乐龄");
                break;
            case R.id.ll_college_certification:
                ToastUtils.showShort(EditInfoActivity.this, "点击大学生认证");
                break;
            default:
                break;
        }
    }

    private void initView() {
        //ToolBar
        navigationIcon = (Toolbar) findViewById(R.id.toolbar_edit_info);

        //LineaLayout
        portrait = (LinearLayout) findViewById(R.id.ll_head_portrait);
        nickname = (LinearLayout) findViewById(R.id.ll_nickname);
        sex = (LinearLayout) findViewById(R.id.ll_sex);
        district = (LinearLayout) findViewById(R.id.ll_district);
        birthday = (LinearLayout) findViewById(R.id.ll_birthday);
        profession = (LinearLayout) findViewById(R.id.ll_profession);
        signature = (LinearLayout) findViewById(R.id.ll_signature);
        hobbies = (LinearLayout) findViewById(R.id.ll_hobbies_interests);
        account = (LinearLayout) findViewById(R.id.ll_account);
        id = (LinearLayout) findViewById(R.id.ll_kuGou_id);
        age = (LinearLayout) findViewById(R.id.ll_age);
        certification = (LinearLayout) findViewById(R.id.ll_college_certification);

        //TextView
        tv_account = (TextView) findViewById(R.id.tv_account);
        tv_account_text = (TextView) findViewById(R.id.tv_account_text);
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_id_text = (TextView) findViewById(R.id.tv_id_text);
        tv_nickname = (TextView) findViewById(R.id.tv_editInfo_content_nickname);
    }

    private void setOnclick() {
        navigationIcon.setNavigationOnClickListener(this);
        portrait.setOnClickListener(this);
        nickname.setOnClickListener(this);
        sex.setOnClickListener(this);
        district.setOnClickListener(this);
        birthday.setOnClickListener(this);
        profession.setOnClickListener(this);
        signature.setOnClickListener(this);
        hobbies.setOnClickListener(this);
        account.setOnClickListener(this);
        id.setOnClickListener(this);
        age.setOnClickListener(this);
        certification.setOnClickListener(this);
    }
}
