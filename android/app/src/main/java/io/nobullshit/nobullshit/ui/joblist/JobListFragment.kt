package io.nobullshit.nobullshit.ui.joblist

import android.os.Bundle
import android.view.View
import androidx.paging.PagedList
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.firebase.ui.firestore.paging.LoadingState

import io.nobullshit.nobullshit.R
import io.nobullshit.nobullshit.base.BaseFragment
import io.nobullshit.nobullshit.db.dao.JobDao
import io.nobullshit.nobullshit.extension.openBrowser
import io.nobullshit.nobullshit.model.Job
import org.koin.android.ext.android.inject
import kotlinx.android.synthetic.main.fragment_job_list.fragment_job_list_rv as recyclerView
import kotlinx.android.synthetic.main.fragment_job_list.fragment_job_list_refresh as refreshLayout

/**
 * A simple [BaseFragment] subclass that list [Job].
 *
 */
class JobListFragment : BaseFragment() {

    val jobDao: JobDao by inject()

    override fun getLayoutById(): Int = R.layout.fragment_job_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.configureOrReloadRecyclerView()
        this.configureRefreshLayout()
    }

    // ---

    private fun configureOrReloadRecyclerView() {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPrefetchDistance(10)
            .setPageSize(20)
            .build()
        val options = FirestorePagingOptions.Builder<Job>()
            .setLifecycleOwner(this)
            .setQuery(this.jobDao.listApprovedJobs(), config, Job::class.java)
            .build()
        this.recyclerView.adapter = JobListAdapter(options, object: JobListAdapter.Listener {
            override fun onClick(id: String?, job: Job) {
                openBrowser(job.url)
            }
            override fun onLoadingStateChanged(state: LoadingState) {
                refreshLayout.isRefreshing = (state == LoadingState.LOADING_INITIAL || state == LoadingState.LOADING_MORE)
            }
        })
    }

    private fun configureRefreshLayout(){
        this.refreshLayout.setColorSchemeResources(R.color.colorPrimary)
        this.refreshLayout.setOnRefreshListener {
            (this.recyclerView.adapter as JobListAdapter).stopListening()
            this.configureOrReloadRecyclerView()
        }
    }
}
