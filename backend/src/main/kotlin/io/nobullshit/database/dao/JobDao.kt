package io.nobullshit.database.dao

import com.google.api.core.ApiFuture
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.Firestore
import io.nobullshit.model.Job

class JobDao(fireStore: Firestore): BaseDao(fireStore) {

    /**
     * Return the main root collection for the [Job]
     */
    override fun mainCollection(): String = "jobs"

    /**
     * Create a job in Firestore
     */
    fun createJob(job: Job): ApiFuture<DocumentReference> = firestore.collection(mainCollection()).add(job)
}