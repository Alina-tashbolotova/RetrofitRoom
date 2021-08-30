package com.example.retrofit.diff;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.retrofit.model.EpisodeModel;

import org.jetbrains.annotations.NotNull;

public class EpisodeDiffUtil extends DiffUtil.ItemCallback<EpisodeModel> {
    @Override
    public boolean areItemsTheSame(@NonNull @NotNull EpisodeModel oldItem, @NonNull @NotNull EpisodeModel newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull @NotNull EpisodeModel oldItem, @NonNull @NotNull EpisodeModel newItem) {
        return oldItem == newItem;
    }
}
