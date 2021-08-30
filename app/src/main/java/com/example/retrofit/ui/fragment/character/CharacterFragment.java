package com.example.retrofit.ui.fragment.character;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.R;
import com.example.retrofit.base.BaseFragment;
import com.example.retrofit.databinding.FragmentCharacterBinding;
import com.example.retrofit.ui.adapters.CharacterAdapter;

public class CharacterFragment extends BaseFragment<CharacterViewModel, FragmentCharacterBinding> {

    private CharacterAdapter characterAdapter = new CharacterAdapter();
    private LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int postVisiblesItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding = FragmentCharacterBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();

    }

    @Override
    protected void initialize() {
        super.initialize();
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        setupRecycler();
    }

    @Override
    protected void setupRecycler() {
        super.setupRecycler();
        linearLayoutManager = new LinearLayoutManager(getContext());
        viewBinding.recyclerCharacter.setLayoutManager(linearLayoutManager);
        viewBinding.recyclerCharacter.setAdapter(characterAdapter);

        characterAdapter.setOnItemClickListener(position -> {
            if (checkInternet()) {
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigate(CharacterFragmentDirections
                                .actionCharacterFragmentToCharacterDetailFragment()
                                .setPhoto(position));
                Toast.makeText(CharacterFragment.this.requireContext(), "Click position" + position, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CharacterFragment.this.getContext(), "Нет интернета!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void setupRequests() {
        if (!fetchInternetCharacter()) {
            characterAdapter.submitList(viewModel.getCharacters());
        }

        viewBinding.recyclerCharacter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getItemCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    postVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + postVisiblesItems) >= totalItemCount) {
                        viewModel.page++;
                        fetchInternetCharacter();
                    }
                }
            }
        });
    }

    private boolean fetchInternetCharacter() {
        if (checkInternet()) {
            viewModel.fetchCharacter().observe(getViewLifecycleOwner(), characterModelRickAndMortyResponse -> {
                characterAdapter.submitList(characterModelRickAndMortyResponse.getResults());
            });
            return true;
        } else {
            return false;
        }
    }
}