package com.example.retrofit.ui.fragment.character.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.retrofit.base.BaseFragment;
import com.example.retrofit.databinding.FragmentCharacterDetailBinding;
import com.example.retrofit.ui.fragment.character.CharacterViewModel;


public class CharacterDetailFragment extends BaseFragment<CharacterViewModel, FragmentCharacterDetailBinding> {

    CharacterViewModel viewModel;
    FragmentCharacterDetailBinding binding;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
    }

    @Override
    protected void setupRequests() {
        id = CharacterDetailFragmentArgs.fromBundle(getArguments()).getPhoto();

        viewModel.fetchId(id).observe(getViewLifecycleOwner(), characterModel -> {
            Glide.with(binding.imageItemCharacterDetail)
                    .load(characterModel.getImage())
                    .into(binding.imageItemCharacterDetail);
            binding.txtItemName
                    .setText(characterModel.getName());
            binding.txtItemStatus
                    .setText(characterModel.getStatus());
            binding.txtItemType
                    .setText(characterModel.getType());
        });
    }

}