package io.nobullshit.database

import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.FirestoreOptions
import io.nobullshit.database.dao.JobDao

class Database {
    /**
     * Return an instance of Firestore
     */
    val service: Firestore = FirestoreOptions.newBuilder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()
            .service

    /**
     * Return an instance of [JobDao]
     */
    val jobDao = JobDao(service)

    /**
     * Closes the gRPC channels associated with this instance and frees up their resources.
     */
    fun close() = service.close()

}