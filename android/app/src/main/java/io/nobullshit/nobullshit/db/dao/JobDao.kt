package io.nobullshit.nobullshit.db.dao

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.nobullshit.nobullshit.model.Job
import io.nobullshit.nobullshit.testing.OpenForTesting

@OpenForTesting
class JobDao(firestore: FirebaseFirestore): BaseDao(firestore) {

    override val rootCollection = "jobs"

    /**
     * List all approved [Job]
     * ordered by [Job#createdAt]
     */
    fun listApprovedJobs() = service()
            .orderBy(Job::createdAt.name, Query.Direction.DESCENDING)
            .whereEqualTo(Job::approved.name, true)
}