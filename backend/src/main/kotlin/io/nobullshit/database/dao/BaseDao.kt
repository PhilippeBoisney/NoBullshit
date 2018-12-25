package io.nobullshit.database.dao

import com.google.cloud.firestore.Firestore

abstract class BaseDao(protected val firestore: Firestore) {
    abstract fun mainCollection(): String
}