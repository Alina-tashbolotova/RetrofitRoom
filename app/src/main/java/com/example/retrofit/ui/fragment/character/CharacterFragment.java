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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
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
        setUpCharacterRecycler();
        setItem();
        checkInternetCharacter();


    }



    private void initialize() {
        characterViewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);

    }

    private void setUpCharacterRecycler() {
        binding.recyclerCharacter.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerCharacter.setAdapter(characterAdapter);


    }


    private void setUpRequestsCharacter() {
        characterViewModel.fetchCharacter().observe(getViewLifecycleOwner(), characterModelRickAndMortyResponse -> {
            characterAdapter.addList(characterModelRickAndMortyResponse.getResults());

        });

    }

    private void setItem() {

        characterAdapter.setOnItemClickListener(position -> {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigate(CharacterFragmentDirections
                                .actionCharacterFragmentToCharacterDetailFragment()
                                .setPhoto(position));
                Toast.makeText(CharacterFragment.this.requireContext(), "Click position" + position, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(CharacterFragment.this.requireContext(),"Нет интернета" + position,Toast.LENGTH_SHORT).show();

            }

        });

    }

    public void setUpOffRequestsCharacters(){
        characterAdapter.addList(characterViewModel.getCharacters());
    }


    private void checkInternetCharacter() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            setUpRequestsCharacter();

        }
        else{
            setUpOffRequestsCharacters();

        }

    }


}