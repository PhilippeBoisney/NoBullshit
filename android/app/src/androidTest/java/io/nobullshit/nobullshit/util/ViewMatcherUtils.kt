package io.nobullshit.nobullshit.util

import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * [Matcher] helper for [ChipGroup]
 */
fun atPositionChipGroup(position: Int, @NonNull itemMatcher: Matcher<View>): Matcher<View> {
    checkNotNull(itemMatcher)
    return object : BoundedMatcher<View, ChipGroup>(ChipGroup::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(view: ChipGroup): Boolean {
            val chip = view.getChildAt(position) as Chip
            return itemMatcher.matches(chip)
        }
    }
}

/**
 * [Matcher] helper for [SwipeRefreshLayout]
 * Check if it's refreshing
 */
fun isRefreshing(): Matcher<View> {
    return object : BoundedMatcher<View, SwipeRefreshLayout>(
        SwipeRefreshLayout::class.java) {

        override fun describeTo(description: Description) {
            description.appendText("is refreshing")
        }

        override fun matchesSafely(view: SwipeRefreshLayout): Boolean {
            return view.isRefreshing
        }
    }
}

/**
 * [Matcher] helper for [RecyclerView]
 * Count the number of its items
 */
fun hasItemCount(itemCount: Int): Matcher<View> {
    return object : BoundedMatcher<View, RecyclerView>(
        RecyclerView::class.java) {

        override fun describeTo(description: Description) {
            description.appendText("has $itemCount items")
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            return view.adapter?.itemCount == itemCount
        }
    }
}