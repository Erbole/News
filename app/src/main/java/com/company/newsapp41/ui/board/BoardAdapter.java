package com.company.newsapp41.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.company.newsapp41.R;
import com.company.newsapp41.models.Board;

import java.util.ArrayList;

import kotlin.jvm.internal.Lambda;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private ArrayList<Board> list;

    public BoardAdapter() {
        list = new ArrayList<>();
        list.add(new Board("Welcome", "In this app you can publish the news", R.drawable.image_1));
        list.add(new Board("Tell what you think", "Comment on the news and discuss it with other users", R.drawable.image_4));
        list.add(new Board("Let's start", "Click on the Start to continue", R.drawable.image_3));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private Button btnStart;
        private ImageView boardPhoto;
        private TextView textDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            boardPhoto = itemView.findViewById(R.id.imageBoard);
            textDesc = itemView.findViewById(R.id.description);
            btnStart = itemView.findViewById(R.id.btn_start);
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).popBackStack();
                }
            });
        }

        public void bind(int position) {
            Board board = list.get(position);
            textTitle.setText(board.getTitle());
            textDesc.setText(board.getDesc());
            boardPhoto.setImageResource(board.getImage());
            if (position == list.size() - 1){
                btnStart.setVisibility(View.VISIBLE);
            } else {
                btnStart.setVisibility(View.INVISIBLE);
            }
        }
    }
}
