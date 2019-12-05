package android.hhh.com.kugou.xiongli.adapters;

import android.app.Activity;
import android.content.Context;
import android.hhh.com.kugou.R;
import android.hhh.com.kugou.xiongli.beans.Song;
import android.hhh.com.kugou.xiongli.utils.MusicUtil;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vip98 on 2019/12/4.
 */

public class LocalMusicAdapter extends BaseAdapter {
    private Context context;
    private List<Song> list;

    public LocalMusicAdapter(Activity activity, List<Song> list){
        this.context = activity;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            //引入布局
            view = View.inflate(context, R.layout.item_local_music, null);
            //实例化对象
            holder.song = (TextView) view.findViewById(R.id.item_mymusic_song);
            holder.singer = (TextView) view.findViewById(R.id.item_mymusic_singer);
            holder.duration = (TextView) view.findViewById(R.id.item_mymusic_duration);
            holder.position = (TextView) view.findViewById(R.id.item_mymusic_postion);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //给控件赋值
        holder.song.setText(list.get(position).song.toString());
        holder.singer.setText(list.get(position).singer.toString());
        //时间需要转换一下
        int duration = list.get(position).duration;
        String time = MusicUtil.formatTime(duration);
        holder.duration.setText(time);
        holder.position.setText(position+1+"");
        return view;
    }
    class ViewHolder{
        TextView song;//歌曲名
        TextView singer;//歌手
        TextView duration;//时长
        TextView position;//序号

    }
}
