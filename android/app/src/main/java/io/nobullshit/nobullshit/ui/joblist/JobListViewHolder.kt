package io.nobullshit.nobullshit.ui.joblist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import io.nobullshit.nobullshit.extension.getCategoryColor
import io.nobullshit.nobullshit.extension.getCategoryTitle
import io.nobullshit.nobullshit.extension.getTypeColor
import io.nobullshit.nobullshit.extension.getTypeTitle
import io.nobullshit.nobullshit.model.Job
import io.nobullshit.nobullshit.utils.createChip
import kotlinx.android.synthetic.main.item_job_list.view.item_job_root_view as rootView
import kotlinx.android.synthetic.main.item_job_list.view.item_job_title as title
import kotlinx.android.synthetic.main.item_job_list.view.item_job_company_title as companyTitle
import kotlinx.android.synthetic.main.item_job_list.view.item_job_company_logo as companyLogo
import kotlinx.android.synthetic.main.item_job_list.view.item_job_chip_group as chipGroup

class JobListViewHolder(parent: View): RecyclerView.ViewHolder(parent) {

    private var callback: JobListAdapter.Listener? = null

    fun bindTo(id: String?, job: Job){
        Glide.with(itemView)
            .load(job.company.logoUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(itemView.companyLogo)
        itemView.title.text = job.title
        itemView.companyTitle.text = job.company.title
        itemView.chipGroup.removeAllViews()
        itemView.chipGroup.addView(createChip(itemView.context, job.getCategoryTitle(itemView.context), job.getCategoryColor()))
        itemView.chipGroup.addView(createChip(itemView.context, job.getTypeTitle(itemView.context), job.getTypeColor()))
        itemView.rootView.setOnClickListener { this.callback?.onClick(id, job) }
    }

    fun registerCallback(callback: JobListAdapter.Listener?) { this.callback = callback }
    fun unRegisterCallback() { this.callback = null }
}