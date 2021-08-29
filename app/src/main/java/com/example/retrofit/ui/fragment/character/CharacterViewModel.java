package com.example.retrofit.ui.fragment.character;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofit.base.BaseViewModel;
import com.example.retrofit.data.repositories.CharacterRepository;
import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.RickAndMortyResponse;

import java.util.List;

public class CharacterViewModel extends BaseViewModel {

    private final CharacterRepository characterRepository = new CharacterRepository();
    public int page = 1;

    public MutableLiveData<RickAndMortyResponse<CharacterModel>> fetchCharacter() {
        return characterRepository.fetchCharacter(page);
    }

    public MutableLiveData<CharacterModel> fetchId(int id) {
        return characterRepository.fetchId(id);
    }

    public List<CharacterModel> getCharacters() {
        return characterRepository.getCharacters();
    }


}


