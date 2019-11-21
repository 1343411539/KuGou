package android.hhh.com.kugou;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private LinearLayout menuLayout;
    private FrameLayout searchLayout;
    private List<Fragment> fragmentList;
    private ImageView serachImage;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //动态设置 menuLayout和searchLayout 的大小
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        menuLayout = (LinearLayout) findViewById(R.id.menu_layout);
        searchLayout = (FrameLayout) findViewById(R.id.search_layout);
        serachImage = (ImageView) findViewById(R.id.search_image);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth, screenHeight / 12);
        menuLayout.setLayoutParams(params);
        searchLayout.setLayoutParams(params);
        //构造适配器
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new MeFragment());
        fragments.add(new ListenFragment());
        fragments.add(new LookFragment());
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);
        ViewPager vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(adapter);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void search_click(View view) {
        serachImage.setVisibility(View.INVISIBLE);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
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

    public class FragAdapter extends FragmentPagerAdapter {
        public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            fragmentList = fragments;
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragmentList.get(arg0);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
