package io.nobullshit.nobullshit.utils

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import io.nobullshit.nobullshit.R

/**
 * Create and return a chip [Chip]
 * with a text [String] and a background color [Int]
 */
fun createChip(context: Context, text: String, @ColorRes color: Int): Chip {
    val chip = Chip(context)
    val chipDrawable = ChipDrawable.createFromResource(context, R.xml.chip)
    chipDrawable.setText(text)
    chip.setChipDrawable(chipDrawable)
    chip.chipBackgroundColor = ContextCompat.getColorStateList(context, color)
    chip.isClickable = false
    return chip
}