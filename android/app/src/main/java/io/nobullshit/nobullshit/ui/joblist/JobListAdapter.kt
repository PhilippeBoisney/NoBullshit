package io.nobullshit.nobullshit.ui.joblist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedList
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.firebase.ui.firestore.paging.LoadingState
import com.google.firebase.firestore.DocumentSnapshot
import io.nobullshit.nobullshit.R
import io.nobullshit.nobullshit.model.Job

class JobListAdapter(options: FirestorePagingOptions<Job>,
                     private val callback: Listener?): FirestorePagingAdapter<Job, JobListViewHolder>(options) {

    interface Listener {
        fun onClick(id: String?, job: Job)
        fun onLoadingStateChanged(state: LoadingState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_job_list, parent, false)
        return JobListViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobListViewHolder, position: Int, job: Job) {
        holder.bindTo(getItem(position)?.id, job)
    }

    override fun onViewAttachedToWindow(holder: JobListViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.registerCallback(callback)
    }

    override fun onViewDetachedFromWindow(holder: JobListViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unRegisterCallback()
    }

    override fun onLoadingStateChanged(state: LoadingState) {
        super.onLoadingStateChanged(state)
        callback?.onLoadingStateChanged(state)
    }
}