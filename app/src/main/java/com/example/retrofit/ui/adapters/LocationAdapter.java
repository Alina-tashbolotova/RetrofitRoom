package com.example.retrofit.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.databinding.ItemLocationBinding;
import com.example.retrofit.model.LocationModel;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    OnItemClickListener onItemClickListener;
    private List<LocationModel> list = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<LocationModel> list) {
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    public void setOnItemClickListener(LocationAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemLocationBinding binding;

        public ViewHolder(ItemLocationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v -> {
                onItemClickListener.onItemClick(getAdapterPosition());

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
