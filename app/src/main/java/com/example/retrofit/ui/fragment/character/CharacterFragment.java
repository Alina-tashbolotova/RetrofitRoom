package com.example.retrofit.ui.fragment.character;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.retrofit.R;
import com.example.retrofit.databinding.FragmentCharacterBinding;
import com.example.retrofit.ui.adapters.CharacterAdapter;


public class CharacterFragment extends Fragment {

    private CharacterViewModel characterViewModel;
    private FragmentCharacterBinding binding;
    private CharacterAdapter characterAdapter = new CharacterAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setUpRequests();
        setItem();


    }

    private void setItem() {
        characterAdapter.setOnItemClickListener(new CharacterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigate(CharacterFragmentDirections
                                .actionCharacterFragmentToCharacterDetailFragment()
                        .setPhoto(position));
            }
        });


    }

    private void setUpRequests() {
     characterViewModel.fetchCharacter().observe(getViewLifecycleOwner(), characterModelRickAndMortyResponse -> {
         characterAdapter.addList(characterModelRickAndMortyResponse.getResults());


     });

    }

    private void initialize() {
        characterViewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        setUpCharacterRecycler();
    }

    private void setUpCharacterRecycler() {
        binding.recyclerCharacter.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerCharacter.setAdapter(characterAdapter);

        characterAdapter.setOnItemClickListener(position -> {
            Toast.makeText(requireContext(), "Click position" + position, Toast.LENGTH_SHORT).show();

        });

    }
}