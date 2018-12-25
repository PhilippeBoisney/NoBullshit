import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.FirestoreException
import io.ktor.http.*
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.nobullshit.configureApplication
import io.nobullshit.database.Database
import io.nobullshit.main
import io.nobullshit.model.Company
import io.nobullshit.model.Job
import io.nobullshit.registerRoutes
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import java.lang.Exception
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Integration tests for the module [configureApplication].
 * Uses [testApp] in test methods to simplify the testing.
 */
class ApplicationTest {

    /**
     * A [mockk] instance of the [Database] to used to verify and mock calls on the integration tests.
     */
    private val db = mockk<Database>(relaxed = true)

    @Before
    fun setup(){
        every { db.service } returns mockk(relaxed = true)
    }

    /**
     * Tests that the index page shows the appropriate html elements.
     */
    @Test
    fun testIndex() = testApp {
        handleRequest(HttpMethod.Get, "/").apply {
            assertEquals(200, response.status()?.value)
            assertTrue(response.content!!.contains("Perfect jobs only. No Bullshit."))
            assertTrue(response.content!!.contains("Each job submitted is reviewed by an expert developer."))
            assertTrue(response.content!!.contains("We publish only <strong>the best.</strong>"))
            assertTrue(response.content!!.contains("See the jobs"))
            assertTrue(response.content!!.contains("Submit a Job"))
        }
    }

    /**
     * Verifies the behaviour of a job creation success.
     * That it should be showing a success message.
     */
    @Test
    fun testJobCreationSuccess() = testApp {
        val fakeJob = Job("Senior Android Developer",
                "https://www.google.com/jobs/convert-all-android-sdk-to-kotlin",
                0, 0,
                Company("Google", "https://www.google.com/logo"))

        handleRequest(HttpMethod.Post, "/submit"){
            addHeader(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
            setBody(listOf("jobTitle" to fakeJob.title,
                    "jobUrl" to fakeJob.url,
                    "jobCategory" to fakeJob.category.toString(),
                    "jobType" to fakeJob.type.toString(),
                    "companyTitle" to fakeJob.company.title,
                    "companyLogoUrl" to fakeJob.company.logoUrl).formUrlEncode())
        }.apply {
            assertEquals(200, response.status()?.value)
            assertTrue(response.content!!.contains("The job was successfully created !"))
            assertFalse(response.content!!.contains("An error happened during job creation..."))
        }
    }

    /**
     * Verifies the behaviour of a job creation failure.
     * That it should be showing an error message.
     */
    @Test
    fun testJobCreationFail() = testApp {
        handleRequest(HttpMethod.Post, "/submit"){
            addHeader(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
        }.apply {
            assertEquals(200, response.status()?.value)
            assertFalse(response.content!!.contains("The job was successfully created !"))
            assertTrue(response.content!!.contains("An error happened during job creation..."))
        }
    }

    /**
     * Private method used to reduce boilerplate when testing the application.
     */
    private fun testApp(callback: TestApplicationEngine.() -> Unit) {
        withTestApplication({ configureApplication(db) }) { callback() }
    }
}