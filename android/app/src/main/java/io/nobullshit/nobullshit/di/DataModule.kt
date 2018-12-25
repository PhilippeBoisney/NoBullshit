package io.nobullshit.nobullshit.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import io.nobullshit.nobullshit.db.dao.JobDao

@Module
class DataModule {
    @Provides
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideJobDao(firestore: FirebaseFirestore): JobDao = JobDao(firestore)
}

