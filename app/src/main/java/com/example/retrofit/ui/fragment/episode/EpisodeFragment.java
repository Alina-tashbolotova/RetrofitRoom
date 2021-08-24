package com.example.retrofit.ui.fragment.episode;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.retrofit.databinding.FragmentEpisodeBinding;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RickAndMortyResponse;
import com.example.retrofit.ui.adapters.EpisodeAdapter;

public class EpisodeFragment extends Fragment {
    private EpisodeViewModel episodeViewModel;
    private FragmentEpisodeBinding binding;
    private EpisodeAdapter episodeAdapter = new EpisodeAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setUpRequests();
    }

    private void setUpRequests() {
        episodeViewModel.fetchEpisode().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onChanged(RickAndMortyResponse<EpisodeModel> episodeModelRickAndMortyResponse) {
                episodeAdapter.addList(episodeModelRickAndMortyResponse.getResults());

            }
        });
    }

    private void initialize() {
        episodeViewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        setEpisodeRecycler();

    }

    private void setEpisodeRecycler() {
        binding.recyclerEpisode.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerEpisode.setAdapter(episodeAdapter);

        episodeAdapter.setOnItemClickListener(position -> {
            Toast.makeText(requireContext(), "Click position" + position, Toast.LENGTH_SHORT).show();

        });
    }
}