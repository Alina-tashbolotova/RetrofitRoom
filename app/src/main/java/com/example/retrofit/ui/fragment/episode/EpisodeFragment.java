package com.example.retrofit.ui.fragment.episode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.base.BaseFragment;
import com.example.retrofit.databinding.FragmentEpisodeBinding;
import com.example.retrofit.ui.adapters.EpisodeAdapter;

public class EpisodeFragment extends BaseFragment<EpisodeViewModel, FragmentEpisodeBinding> {

    private EpisodeAdapter episodeAdapter = new EpisodeAdapter();
    private LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int postVisiblesItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding = FragmentEpisodeBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }

    @Override
    protected void initialize() {
        super.initialize();
        viewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        setupRecycler();
    }


    @Override
    protected void setupRecycler() {
        super.setupRecycler();
        linearLayoutManager = new LinearLayoutManager(getContext());
        viewBinding.recyclerEpisode.setLayoutManager(linearLayoutManager);
        viewBinding.recyclerEpisode.setAdapter(episodeAdapter);

        episodeAdapter.setOnItemClickListener(position -> {
            Toast.makeText(requireContext(), "Click position" + position, Toast.LENGTH_SHORT).show();

        });
    }


    @Override
    protected void setupRequests() {
        if (!fetchInternetEpisode()) {
            episodeAdapter.submitList(viewModel.getEpisode());
        }

        viewBinding.recyclerEpisode.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getItemCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    postVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + postVisiblesItems) >= totalItemCount) {
                        viewModel.page++;
                        fetchInternetEpisode();
                    }
                }
            }
        });
    }


    private boolean fetchInternetEpisode() {
        if (checkInternet()) {
            viewModel.fetchEpisode().observe(getViewLifecycleOwner(), episodeModelRickAndMortyResponse -> {
                episodeAdapter.submitList(episodeModelRickAndMortyResponse.getResults());
            });
            return true;
        } else {
            return false;
        }
    }
}