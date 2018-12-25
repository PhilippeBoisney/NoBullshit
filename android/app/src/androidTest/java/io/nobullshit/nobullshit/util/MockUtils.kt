package io.nobullshit.nobullshit.util

import com.google.android.gms.tasks.TaskExecutors
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.*
import io.mockk.every
import io.mockk.mockk
import java.util.concurrent.Callable
import java.util.concurrent.Executor

/**
 * Mock a [FirebaseFirestore] [Query]
 * with custom generic object(s) [models]
 */
fun <T> mockQuery(valueType: Class<T>, vararg models: T): Query {

    // First query that getting mocked data
    val mockQuery = mockk<Query>()
    val mockSnapshot = mockk<QuerySnapshot>()
    val mockDocuments = mutableListOf<QueryDocumentSnapshot>()

    /**
     * Create a mock of each [DocumentSnapshot]
     * and fill it with an object [T] from [models]
     */
    models.forEach {
        val mockDocument = mockk<QueryDocumentSnapshot>(relaxed = true)
        every { mockDocument.toObject(valueType) } returns it
        every { mockQuery.startAfter(mockDocument) } returns mockQuery
        mockDocuments.add(mockDocument)
    }
    every { mockSnapshot.documents } returns mockDocuments.toList()

    // Second query that must return EMPTY data to cancel future loads
    val mockQueryEmpty = mockk<Query>(relaxed = true)
    val mockSnapshotEmpty = mockk<QuerySnapshot>()
    every { mockSnapshotEmpty.documents } returns listOf<DocumentSnapshot>()

    /**
     * Mocking two requests [Tasks].
     * The first one will get mocked data.
     * The second will return empty list of [DocumentSnapshot].
     * Both tasks use [TaskExecutors.MAIN_THREAD] executor.
     */
    every { mockQuery.get(Source.DEFAULT) } returns Tasks.call { mockSnapshot }
    every { mockQuery.limit(60) } returns mockQuery
    every { mockQuery.limit(20) } returns mockQueryEmpty
    every { mockQueryEmpty.get(Source.DEFAULT) } returns Tasks.call { mockSnapshotEmpty }

    return mockQuery
}