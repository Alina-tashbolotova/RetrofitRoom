package com.example.retrofit.ui.fragment.character;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

    private CharacterViewModel characterViewModel;
    private FragmentCharacterBinding binding;
    private CharacterAdapter characterAdapter = new CharacterAdapter();
    private LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int postVisiblesItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    protected void initialize() {
        super.initialize();
        characterViewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        setupRecycler();
    }

    @Override
    protected void setupRecycler() {
        super.setupRecycler();
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerCharacter.setLayoutManager(linearLayoutManager);
        binding.recyclerCharacter.setAdapter(characterAdapter);

        characterAdapter.setOnItemClickListener(position -> {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigate(CharacterFragmentDirections
                                .actionCharacterFragmentToCharacterDetailFragment()
                                .setPhoto(position));
                Toast.makeText(CharacterFragment.this.requireContext(), "Click position" + position, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CharacterFragment.this.requireContext(), "Нет интернета" + position, Toast.LENGTH_SHORT).show();

            }

        });
    }

    @Override
    protected void setupRequests() {
        super.setupRequests();
        characterViewModel.fetchCharacter().observe(getViewLifecycleOwner(), characterModelRickAndMortyResponse -> {
            characterAdapter.addList(characterModelRickAndMortyResponse.getResults());

        });
        binding.recyclerCharacter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getItemCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    postVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + postVisiblesItems) >= totalItemCount) {
                        characterViewModel.page++;
                        characterViewModel.fetchCharacter().observe(getViewLifecycleOwner(), characterModelRickAndMortyResponse -> {
                            characterAdapter.addList(characterModelRickAndMortyResponse.getResults());
                        });

                    }

                }
            }
        });


    }


    @Override
    protected void setupOffRequests() {
        super.setupOffRequests();
        characterAdapter.addList(characterViewModel.getCharacters());

    }


    @Override
    protected void checkInternet() {
        super.checkInternet();
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            setupRequests();

        } else {
            setupOffRequests();

        }


    }


}