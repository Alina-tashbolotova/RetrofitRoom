package com.example.retrofit.diff;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.retrofit.model.LocationModel;

import org.jetbrains.annotations.NotNull;

public class LocationDiffUtil extends DiffUtil.ItemCallback<LocationModel> {
    @Override
    public boolean areItemsTheSame(@NonNull @NotNull LocationModel oldItem, @NonNull @NotNull LocationModel newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull @NotNull LocationModel oldItem, @NonNull @NotNull LocationModel newItem) {
        return oldItem == newItem;
    }
}
