package com.example.retrofit.ui.fragment.episode;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofit.base.BaseViewModel;
import com.example.retrofit.data.repositories.EpisodeRepository;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RickAndMortyResponse;

import java.util.List;

public class EpisodeViewModel extends BaseViewModel {

    private final EpisodeRepository episodeRepository = new EpisodeRepository();
    public int page = 1;

    public MutableLiveData<RickAndMortyResponse<EpisodeModel>> fetchEpisode() {
        return episodeRepository.fetchEpisode(page);

    }

    public List<EpisodeModel> getEpisode() {
        return episodeRepository.getEpisode();
    }

}
