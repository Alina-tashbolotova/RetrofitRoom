package com.example.retrofit.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.databinding.ItemEpisodeBinding;
import com.example.retrofit.diff.EpisodeDiffUtil;
import com.example.retrofit.iterfaces.OnItemClickListener;
import com.example.retrofit.model.EpisodeModel;

public class EpisodeAdapter extends ListAdapter<EpisodeModel, EpisodeAdapter.ViewHolder> {

    private OnItemClickListener listener;
    private EpisodeDiffUtil episodeDiffUtil;

    public EpisodeAdapter() {
        super(new EpisodeDiffUtil());
    }

    @NonNull
    @Override
    public EpisodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EpisodeAdapter.ViewHolder(
                ItemEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.ViewHolder holder, int position) {
        holder.onBind(getItem(position));

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemEpisodeBinding binding;

        public ViewHolder(@NonNull ItemEpisodeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v -> {
                listener.onItemClickListener(getAdapterPosition());

            });
        }

        private void onBind(EpisodeModel item) {
            binding.txtItemName.setText(item.getName());
            binding.txtItemEpisode.setText(item.getEpisode());
            binding.txtItemAirDate.setText(item.getAir_date());
            binding.txtItemCreated.setText(item.getCreated());
        }
    }
}
