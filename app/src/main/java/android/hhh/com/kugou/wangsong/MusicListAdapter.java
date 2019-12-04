package android.hhh.com.kugou.wangsong;

import android.hhh.com.kugou.R;
import android.hhh.com.kugou.huang.SearchList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>{
    private List<SearchList> MusicList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView musicName;
        TextView author;

        public ViewHolder (View view)
        {
            super(view);
            musicName = view.findViewById(R.id.music_tv);
            author = view.findViewById(R.id.author_tv);
        }

    }

    public MusicListAdapter(List<SearchList> MusicList){
        this.MusicList = MusicList;
    }

    @Override
    public MusicListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MusicListAdapter.ViewHolder viewHolder, int i) {
        SearchList searchList =MusicList.get(i);
        viewHolder.musicName.setText(searchList.getMusicName());
        viewHolder.author.setText(searchList.getWriter());
    }

    @Override
    public int getItemCount() {
        return MusicList.size();
    }
}
