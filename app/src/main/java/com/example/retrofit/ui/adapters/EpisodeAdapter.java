package com.example.retrofit.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.databinding.ItemEpisodeBinding;

import com.example.retrofit.model.EpisodeModel;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;
    private ItemEpisodeBinding binding;
    private ArrayList<EpisodeModel> list = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding.getRoot());

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(ArrayList<EpisodeModel> models){
        this.list = models;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            binding.getRoot().setOnClickListener(v -> {
                onItemClickListener.onItemClick(getAdapterPosition());

            });
        }
        private void onBind(EpisodeModel item) {
            binding.txtItemName.setText(item.getName());
            binding.txtItemEpisode.setText(item.getEpisode());
            binding.txtItemAirDate.setText(item.getAir_date());
            binding.txtItemCreated.setText(item.getCreated());
        }


    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;

    }
}
