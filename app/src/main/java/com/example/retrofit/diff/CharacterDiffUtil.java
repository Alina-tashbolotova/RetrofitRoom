package com.example.retrofit.diff;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.retrofit.model.CharacterModel;

import org.jetbrains.annotations.NotNull;

public class CharacterDiffUtil extends DiffUtil.ItemCallback<CharacterModel> {
    @Override
    public boolean areItemsTheSame(@NonNull @NotNull CharacterModel oldItem, @NonNull @NotNull CharacterModel newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull @NotNull CharacterModel oldItem, @NonNull @NotNull CharacterModel newItem) {
        return oldItem == newItem;
    }
}
