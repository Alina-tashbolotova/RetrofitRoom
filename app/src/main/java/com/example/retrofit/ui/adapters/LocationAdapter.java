package com.example.retrofit.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.databinding.ItemLocationBinding;
import com.example.retrofit.diff.LocationDiffUtil;
import com.example.retrofit.iterfaces.OnItemClickListener;
import com.example.retrofit.model.LocationModel;

import org.jetbrains.annotations.NotNull;

public class LocationAdapter extends ListAdapter<LocationModel, LocationAdapter.ViewHolder> {

    private OnItemClickListener listener;

    public LocationAdapter() {
        super(new LocationDiffUtil());
    }

    @NonNull
    @NotNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new LocationAdapter.ViewHolder(
                ItemLocationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LocationAdapter.ViewHolder holder, int position) {
        holder.onBind(getItem(position));

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemLocationBinding binding;

        public ViewHolder(@NonNull ItemLocationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                listener.onItemClickListener(getAdapterPosition());

            });
        }

        private void onBind(LocationModel item) {
            binding.itemName.setText(item.getName());
            binding.txtItemType.setText(item.getType());
            binding.txtItemCreated.setText(item.getCreated());
            binding.txtItemDimension.setText(item.getDimension());
        }

    }
}
