package io.nobullshit.nobullshit.di

import com.google.firebase.firestore.FirebaseFirestore
import io.nobullshit.nobullshit.db.dao.JobDao
import org.koin.dsl.module.module

/**
 * Remote [FirebaseFirestore] Datasource
 */
val remoteDataSourceModule = module {
    factory { FirebaseFirestore.getInstance() }
    single { JobDao(get()) }
}