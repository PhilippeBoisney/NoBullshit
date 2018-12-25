package io.nobullshit.nobullshit.db.dao

import com.google.firebase.firestore.FirebaseFirestore

/**
 * Base class for CRUD operation on [FirebaseFirestore]
 */
abstract class BaseDao(val firestore: FirebaseFirestore) {
    /**
     * Force child to declare the main root collection
     */
    abstract val rootCollection: String

    /**
     * Ensure all future requests will use the root collection
     */
    protected fun service() = firestore.collection(rootCollection)
}