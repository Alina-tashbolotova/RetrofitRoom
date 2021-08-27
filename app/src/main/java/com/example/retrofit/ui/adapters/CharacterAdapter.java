package com.example.retrofit.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit.databinding.ItemCharacterBinding;
import com.example.retrofit.model.CharacterModel;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {


    private List<CharacterModel> list = new ArrayList<>();

    private OnItemClickListener listener;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<CharacterModel> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemCharacterBinding binding;

        public ViewHolder(ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        private void onBind(CharacterModel item) {

            Glide.with(binding.imageItemCharacter).load(item.getImage()).into(binding.imageItemCharacter);
            binding.txtItemCharacterName.setText(item.getName());
            itemView.setOnClickListener(v ->
                    listener.onItemClick(item.getId()));
        }
    }
}
