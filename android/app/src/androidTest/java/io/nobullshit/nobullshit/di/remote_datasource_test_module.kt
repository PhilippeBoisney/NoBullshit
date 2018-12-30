package io.nobullshit.nobullshit.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.mockk.mockk
import io.nobullshit.nobullshit.db.dao.JobDao
import io.nobullshit.nobullshit.model.Job
import io.nobullshit.nobullshit.util.mockQuery
import org.koin.dsl.module.module

/**
 * Remote [FirebaseFirestore] datasource only used for tests
 * @param listApprovedJobs: Mocked [Query] used to return a custom list of jobs.
 */
fun remoteDataSourceTestModule(listApprovedJobs: Query) = module {
    factory { mockk<FirebaseFirestore>(relaxed = true) }
    single {
        object : JobDao(get()) {
            override fun listApprovedJobs() = listApprovedJobs
        } as JobDao
    }
}