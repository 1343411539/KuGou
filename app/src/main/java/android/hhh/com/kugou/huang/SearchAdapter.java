package android.hhh.com.kugou.huang;

import android.hhh.com.kugou.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<SearchList> mSearchList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView aMusicName;
        TextView aWriter;

        public ViewHolder (View view)
        {
            super(view);
            aMusicName = view.findViewById(R.id.tv_musicname);
            aWriter = view.findViewById(R.id.tv_writer);
        }

    }

    public SearchAdapter(List<SearchList> mSearchList){
        this.mSearchList = mSearchList;
    }

    /**
     * onCreateViewHolder()用于创建ViewHolder实例,并把加载的布局传入到构造函数去,再把ViewHolder实例返回
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * onBindViewHolder()则是用于对子项的数据进行赋值,会在每个子项被滚动到屏幕内时执行,position得到当前项的icon实例
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        SearchList searchList = mSearchList.get(position);
        holder.aMusicName.setText(searchList.getMusicName());
        holder.aWriter.setText(searchList.getWriter());
    }

    /**
     * getItemCount()返回RecyclerView的子项数目
     * @return
     */
    @Override
    public int getItemCount(){
        return mSearchList.size();
    }
}
