package com.company.newsapp41;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.company.newsapp41.databinding.FragmentNewsBinding;
import com.company.newsapp41.model.Model;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private Model model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(LayoutInflater.from(getContext()), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = (Model) requireArguments().getSerializable("update");
        if (model != null) binding.edittext.setText(model.getTitle());

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edittext.getText().toString().isEmpty()){
                    YoYo.with(Techniques.Shake).duration(700).repeat(3).playOn(binding.edittext);
                }
                save();
            }
        });
    }

    private void save() {
        Bundle bundle = new Bundle();
        String text = binding.edittext.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(requireContext(), "type task!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (model == null) {
            model = new Model(System.currentTimeMillis(), text);
            App.getDataBase().newsDao().insert(model);
        }else {
            model.setTitle(text);
        }

        bundle.putSerializable("model", model);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}