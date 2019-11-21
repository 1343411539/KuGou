package android.hhh.com.kugou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MusicPlayActivity extends AppCompatActivity {
    private ImageView roundAlphaImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        roundAlphaImage=(ImageView)findViewById(R.id.round_alpha_image);
        roundAlphaImage.setAlpha(150);

    }
}
