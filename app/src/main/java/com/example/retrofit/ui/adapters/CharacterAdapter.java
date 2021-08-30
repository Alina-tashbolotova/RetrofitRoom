package com.example.retrofit.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit.databinding.ItemCharacterBinding;
import com.example.retrofit.diff.CharacterDiffUtil;
import com.example.retrofit.iterfaces.OnItemClickListener;
import com.example.retrofit.model.CharacterModel;


public class CharacterAdapter extends ListAdapter<CharacterModel, CharacterAdapter.ViewHolder> {

    private OnItemClickListener listener;
    private CharacterDiffUtil characterDiffUtil;


    public CharacterAdapter() {
        super(new CharacterDiffUtil());
    }


    @NonNull
    @Override
    public CharacterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterAdapter.ViewHolder(
                ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.ViewHolder holder, int position) {
        holder.onBind(getItem(position));

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemCharacterBinding binding;

        public ViewHolder(@NonNull ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onBind(CharacterModel item) {

            Glide.with(binding.imageItemCharacter).load(item.getImage()).into(binding.imageItemCharacter);
            binding.txtItemCharacterName.setText(item.getName());
            itemView.setOnClickListener(v ->
                    listener.onItemClickListener(item.getId()));
        }

    }


}

