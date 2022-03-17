package com.company.newsapp41;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.newsapp41.interfaces.OnItemClickListener;
import com.company.newsapp41.models.News;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private ArrayList<News> list;
    private OnItemClickListener onItemClickListener;

    public NewsAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.rootView.setBackgroundColor(Color.WHITE);
            holder.textTitle.setTextColor(Color.BLACK);
            holder.date.setTextColor(Color.GRAY);
        } else {
            holder.rootView.setBackgroundColor(Color.BLACK);
            holder.textTitle.setTextColor(Color.WHITE);
            holder.date.setTextColor(Color.WHITE);
        }
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(News news) {
        list.add(0, news);
        notifyItemInserted(list.indexOf(news));
    }

    public void insertItem(News news, int position) {
        list.set(position, news);
        notifyItemChanged(position);
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyDataSetChanged();
        notifyItemRemoved(list.indexOf(position));
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public News getItem(int position) {
        return list.get(position);
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        LinearLayout rootView;
        private TextView textTitle;
        private TextView date;

        public NewsViewHolder(@NonNull View itemView) {

            super(itemView);
            rootView = (LinearLayout) itemView.findViewById(R.id.back);

            textTitle = itemView.findViewById(R.id.textTitle);
            date = itemView.findViewById(R.id.date_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onItemLongClick(getAdapterPosition());
                    return false;
                }
            });
        }

        public void bind(News news) {
            textTitle.setText(news.getTitle());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm, dd MMMM yyyy ");
            Date resultdate = new Date(news.getCreatedAt());
            date.setText(sdf.format(resultdate));
        }
    }
}