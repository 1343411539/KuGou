package android.hhh.com.kugou.wangsong;

import android.hhh.com.kugou.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>{
    private List<MusicList> mMusicList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView songName;
        TextView authorName;
        TextView duration;

        public ViewHolder (View view)
        {
            super(view);
            songName = view.findViewById(R.id.music_tv);
            authorName = view.findViewById(R.id.author_tv);
            duration=view.findViewById(R.id.music_duration_tv);
        }

    }

    public MusicListAdapter(List<MusicList> mMusicList){
        this.mMusicList = mMusicList;
    }

    @Override
    public MusicListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MusicListAdapter.ViewHolder viewHolder, int i) {
        MusicList musicList =mMusicList.get(i);
        viewHolder.songName.setText(musicList.getSongName());
        viewHolder.authorName.setText(musicList.getAuthorName());
        viewHolder.duration.setText(musicList.getDuration());
    }

    @Override
    public int getItemCount() {
        return mMusicList.size();
    }
}
