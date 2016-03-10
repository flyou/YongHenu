package com.flyou.simplehenu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flyou.simplehenu.R;

import com.flyou.simplehenu.domain.News;

import java.util.List;

/**
 * ============================================================
 * 项目名称：HenuCenterPro
 * 包名称：com.flyou.henucenter.adapter
 * 文件名：NewsRecycleViewAdapter
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2015/12/26 15:03
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 **/
public class NewsRecycleViewAdapter extends RecyclerView.Adapter<NewsRecycleViewAdapter.MyViewHolder> {
    private Context context;
    private List<News> news;

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public NewsRecycleViewAdapter(Context context, List<News> news) {
        this.context=context;
        this.news =news;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.view_news_item, null));

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.title.setText(news.get(position).getTitle());

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);


        }
    }
    public void addItem(News news) {

        this.news.add(news);
        notifyItemInserted(0);
    }

    public void removeItem(int position) {
        news.remove(position);
        notifyItemRemoved(position);
    }
}
