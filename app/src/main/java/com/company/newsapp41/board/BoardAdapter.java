package com.company.newsapp41.board;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.company.newsapp41.R;
import com.company.newsapp41.databinding.PagerBoardBinding;
import com.company.newsapp41.model.Board;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    private ArrayList<Board> list;
    private Button btn_start;
    private int[] lottie = new int[]{R.raw.city, R.raw.city, R.raw.city};

    public BoardAdapter() {
        list = new ArrayList<>();
        list.add(new Board("Качок Доге и плачущий Чимс", "Мемом с собаками породы сиба-ину пользователи сравнивали настоящий момент и прошлое. Победа всегда на стороне Доге, и он, как правило, олицетворяет прошедшие времена"));
        list.add(new Board("Танцующие носильщики гробов", "Танцующие с гробом темнокожие парни были популярны практически весь год. Первые смешные видео с их участием появились в конце февраля. Популярность они набрали в связи с новостями о коронавирусе."));
        list.add(new Board("Наташ, ты спишь?", "Мем «Наташ, ты спишь» стал абсолютным хитом в апреле: с его помощью шутили про коронавирус, самоизоляцию, цифровые пропуска. Потом, в течение года, используя этот шаблон, пользователи обращались к самым разным темам. Этот мем — народный, по мнению Максима Корнева, хотя его и быстро «затаскали» все, кому не лень."));
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PagerBoardBinding view = PagerBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
        private PagerBoardBinding binding;

        public BoardViewHolder(@NonNull PagerBoardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(int position) {
            Board board = list.get(position);
            binding.textTitle.setText(board.getTitle());
            binding.description.setText(board.getDesc());
            binding.animationView.setAnimation(lottie[position]);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                }

            }, 2000);
            if (position == list.size() - 1) {
                binding.btnStart.setVisibility(View.VISIBLE);
            } else {
                binding.btnStart.setVisibility(View.INVISIBLE);
            }
            binding.btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavController navController = Navigation.findNavController((Activity) view.getContext(), R.id.nav_host_fragment_activity_main);
                    navController.navigateUp();
                }
            });
        }
    }
}
