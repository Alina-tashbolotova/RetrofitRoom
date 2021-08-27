package com.example.retrofit.ui.fragment.character.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.retrofit.databinding.FragmentCharacterDetailBinding;
import com.example.retrofit.ui.adapters.CharacterAdapter;
import com.example.retrofit.ui.fragment.character.CharacterViewModel;


public class CharacterDetailFragment extends Fragment {
    int id;
    CharacterViewModel viewModel;
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
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        initialize();
        getInformation();
    }

    private void initialize() {

        id = CharacterDetailFragmentArgs.fromBundle(getArguments()).getPhoto();

    }

    private void getInformation() {
        viewModel.fetchId(id).observe(getViewLifecycleOwner(), characterModel -> {
            Glide.with(binding.imageItemCharacterDetail)
                    .load(characterModel.getImage())
                    .into(binding.imageItemCharacterDetail);
            binding.txtItemName.setText(characterModel.getName());
            binding.txtItemStatus.setText(characterModel.getStatus());
            binding.txtItemType.setText(characterModel.getType());
        });
    }

}