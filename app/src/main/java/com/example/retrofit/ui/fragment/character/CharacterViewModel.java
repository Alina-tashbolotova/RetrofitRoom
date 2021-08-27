package com.example.retrofit.ui.fragment.character;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.data.repositories.CharacterRepository;
import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.RickAndMortyResponse;

import java.util.List;

public class CharacterViewModel extends ViewModel {

    private final CharacterRepository characterRepository = new CharacterRepository();

    public MutableLiveData<RickAndMortyResponse<CharacterModel>> fetchCharacter() {
        return characterRepository.fetchCharacter();
    }

    public MutableLiveData<CharacterModel> fetchId(int id) {
        return characterRepository.fetchId(id);
    }

    public List<CharacterModel> getCharacters() {
        return characterRepository.getCharacters();
    }


}


