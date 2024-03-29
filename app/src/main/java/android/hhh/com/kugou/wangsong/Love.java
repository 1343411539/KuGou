package android.hhh.com.kugou.wangsong;


import android.hhh.com.kugou.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Love extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBackIcon;
    private TextView tvBackIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        initView();
    }

    public void initView(){
        ivBackIcon = (ImageView) findViewById(R.id.iv_backIcon);
        tvBackIcon = (TextView) findViewById(R.id.tv_backIcon);

        ivBackIcon.setOnClickListener(this);
        tvBackIcon.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
