package com.example.retrofit.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.databinding.ItemLocationBinding;
import com.example.retrofit.model.LocationModel;


import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    OnItemClickListener onItemClickListener;
    private ArrayList<LocationModel> list = new ArrayList<>();
    private ItemLocationBinding binding;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemLocationBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new  ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(ArrayList<LocationModel> models){
        this.list = models;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding.getRoot().setOnClickListener(v -> {
                onItemClickListener.onItemClick(getAdapterPosition());

            });

        }
        private void onBind(LocationModel item){
            binding.itemName.setText(item.getName());
            binding.txtItemType.setText(item.getType());
            binding.txtItemCreated.setText(item.getCreated());
            binding.txtItemDimension.setText(item.getDimension());
        }

    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(LocationAdapter.OnItemClickListener listener){
        this.onItemClickListener = listener;

    }
}
