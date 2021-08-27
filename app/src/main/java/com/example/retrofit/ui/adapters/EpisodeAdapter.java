package com.example.retrofit.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.databinding.ItemEpisodeBinding;
import com.example.retrofit.model.EpisodeModel;

import java.util.ArrayList;
import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;
    private List<EpisodeModel> list = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<EpisodeModel> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemEpisodeBinding binding;

        public ViewHolder(ItemEpisodeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
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
}
