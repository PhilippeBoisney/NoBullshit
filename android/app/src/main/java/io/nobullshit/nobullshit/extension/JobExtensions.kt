package io.nobullshit.nobullshit.extension

import android.content.Context
import io.nobullshit.nobullshit.R
import io.nobullshit.nobullshit.model.Job

/**
 * Return the category title [String]
 * relative to an id [Job.Category]
 */
fun Job.getCategoryTitle(context: Context): String = when (category) {
    Job.Category.ANDROID.value -> context.getString(R.string.category_android)
    else -> context.getString(R.string.category_unknown)
}

/**
 * Return the category color [Int]
 * relative to an id [Job.Category]
 */
fun Job.getCategoryColor(): Int = when (category) {
    Job.Category.ANDROID.value -> R.color.android
    else -> R.color.gray_light
}

/**
 * Return the type title [String]
 * relative to an id [Job.Type]
 */
fun Job.getTypeTitle(context: Context): String = when (type) {
    Job.Type.FULL_TIME.value -> context.getString(R.string.type_full_time)
    Job.Type.PART_TIME.value -> context.getString(R.string.type_part_time)
    Job.Type.CONTRACT.value -> context.getString(R.string.type_contract)
    Job.Type.INTERN.value -> context.getString(R.string.type_intern)
    else -> context.getString(R.string.type_unknown)
}

/**
 * Return the type color [Int]
 * relative to an id [Job.Type]
 */
fun Job.getTypeColor(): Int = R.color.gray_light

