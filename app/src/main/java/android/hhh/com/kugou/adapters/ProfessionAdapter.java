package android.hhh.com.kugou.adapters;

import android.hhh.com.kugou.ProfessionActivity;
import android.hhh.com.kugou.R;
import android.hhh.com.kugou.beans.ItemBean;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vip98 on 2019/11/20.
 */

public class ProfessionAdapter extends RecyclerView.Adapter<ProfessionAdapter.ProfessionViewHolder> {
    private List<ItemBean> mData;

    public ProfessionAdapter(List<ItemBean> data) {
        this.mData = data;
    }

    //这个方法用于创建条目View
    @Override
    public ProfessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View view = View.inflate(parent.getContext(), R.layout.item_profession, null);
        //返回ProfessionViewHolder的对象
        return new ProfessionViewHolder(view);
    }

    //这个方法用于绑定holder的，用来设置数据
    @Override
    public void onBindViewHolder(ProfessionViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    //返回条目个数
    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public class ProfessionViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;

        public ProfessionViewHolder(View itemView) {
            super(itemView);
            //初始化条目的控件
            title = itemView.findViewById(R.id.tv_profession);
            icon = itemView.findViewById(R.id.iv_selected);
        }

        public void setData(ItemBean itemBean) {
            title.setText(itemBean.title);
            icon.setImageResource(itemBean.icon);
        }
    }
}
