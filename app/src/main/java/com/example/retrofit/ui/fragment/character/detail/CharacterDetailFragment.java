package com.example.retrofit.ui.fragment.character.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofit.R;
import com.example.retrofit.databinding.FragmentCharacterBinding;
import com.example.retrofit.databinding.FragmentCharacterDetailBinding;

import org.jetbrains.annotations.NotNull;


public class CharacterDetailFragment extends Fragment {
    private FragmentCharacterDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {

    }
}