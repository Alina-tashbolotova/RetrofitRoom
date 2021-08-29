package com.example.retrofit.ui.fragment.episode;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.base.BaseFragment;
import com.example.retrofit.databinding.FragmentEpisodeBinding;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RickAndMortyResponse;
import com.example.retrofit.ui.adapters.EpisodeAdapter;

import org.jetbrains.annotations.NotNull;

public class EpisodeFragment extends BaseFragment<EpisodeViewModel, FragmentEpisodeBinding> {

    private EpisodeViewModel episodeViewModel;
    private FragmentEpisodeBinding binding;
    private EpisodeAdapter episodeAdapter = new EpisodeAdapter();
    private LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int postVisiblesItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    protected void initialize() {
        super.initialize();
        episodeViewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        setupRecycler();
    }


    @Override
    protected void setupRequests() {
        super.setupRequests();
        episodeViewModel.fetchEpisode().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onChanged(RickAndMortyResponse<EpisodeModel> episodeModelRickAndMortyResponse) {
                episodeAdapter.addList(episodeModelRickAndMortyResponse.getResults());

            }
        });
    }


    @Override
    protected void setupRecycler() {
        super.setupRecycler();
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerEpisode.setLayoutManager(linearLayoutManager);
        binding.recyclerEpisode.setAdapter(episodeAdapter);

        episodeAdapter.setOnItemClickListener(position -> {
            Toast.makeText(requireContext(), "Click position" + position, Toast.LENGTH_SHORT).show();

        });

        binding.recyclerEpisode.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getItemCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    postVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + postVisiblesItems) >= totalItemCount) {
                        episodeViewModel.page++;
                        episodeViewModel.fetchEpisode().observe(getViewLifecycleOwner(), episodeModelRickAndMortyResponse -> {
                            episodeAdapter.addList(episodeModelRickAndMortyResponse.getResults());
                        });


                    }

                }

            }
        });
    }

    @Override
    protected void setupOffRequests() {
        super.setupOffRequests();
        episodeAdapter.addList(episodeViewModel.getEpisode());
    }


    @Override
    protected void checkInternet() {
        super.checkInternet();
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            setupRequests();

        } else {
            setupOffRequests();

        }
    }
}