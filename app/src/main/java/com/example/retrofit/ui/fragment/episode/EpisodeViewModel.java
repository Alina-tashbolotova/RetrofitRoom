package com.example.retrofit.ui.fragment.episode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.data.repositories.EpisodeRepository;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RickAndMortyResponse;

import java.util.List;

public class EpisodeViewModel extends ViewModel {

    private final EpisodeRepository episodeRepository = new EpisodeRepository();

    public MutableLiveData<RickAndMortyResponse<EpisodeModel>> fetchEpisode() {
        return episodeRepository.fetchEpisode();

    }

    public List<EpisodeModel> getEpisode() {
        return episodeRepository.getEpisode();
    }

}
