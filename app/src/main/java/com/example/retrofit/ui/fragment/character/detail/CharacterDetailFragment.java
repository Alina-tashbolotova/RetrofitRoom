package com.example.retrofit.ui.fragment.character.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.retrofit.R;
import com.example.retrofit.databinding.FragmentCharacterBinding;
import com.example.retrofit.databinding.FragmentCharacterDetailBinding;
import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.ui.adapters.CharacterAdapter;
import com.example.retrofit.ui.fragment.character.CharacterViewModel;

import org.jetbrains.annotations.NotNull;


public class CharacterDetailFragment extends Fragment {
    private FragmentCharacterDetailBinding binding;
    CharacterAdapter adapter;
    int id;
    CharacterViewModel viewModel;

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
        getInformation();
    }

    private void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        id = CharacterDetailFragmentArgs.fromBundle(getArguments()).getPhoto();

    }

    private void getInformation() {
        viewModel.fetchId(id).observe(getViewLifecycleOwner(),characterModel -> {
            Glide.with(binding.imageItemCharacterDetail)
                    .load(characterModel.getImage())
                    .into(binding.imageItemCharacterDetail);
            binding.txtItemName.setText(characterModel.getName());
            binding.txtItemStatus.setText(characterModel.getStatus());
            binding.txtItemType.setText(characterModel.getType());
        });
    }

}