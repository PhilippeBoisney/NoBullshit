package io.nobullshit.nobullshit.ui.joblist

import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.google.firebase.firestore.*
import io.mockk.*
import io.nobullshit.nobullshit.Datasets
import io.nobullshit.nobullshit.Datasets.SINGLE_JOB
import io.nobullshit.nobullshit.R
import io.nobullshit.nobullshit.db.dao.JobDao
import io.nobullshit.nobullshit.extension.getCategoryTitle
import io.nobullshit.nobullshit.extension.getTypeTitle
import io.nobullshit.nobullshit.model.Company
import io.nobullshit.nobullshit.model.Job
import io.nobullshit.nobullshit.testing.SingleFragmentActivity
import io.nobullshit.nobullshit.util.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented tests for [JobListFragment]
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class TIJobList {

    @Rule
    @JvmField
    val activityRule = IntentsTestRule(SingleFragmentActivity::class.java, true, true)

    @Test
    fun testLoadingSingleJob() {
        this.configureFragmentBeforeTest(mockQuery(Job::class.java, Datasets.SINGLE_JOB))

        onView(withId(R.id.fragment_job_list_refresh)).check(matches(not(isRefreshing())))
        onView(withId(R.id.fragment_job_list_rv)).check(matches((hasItemCount(1))))
        onView(allOf(withId(R.id.item_job_title), withText(Datasets.SINGLE_JOB.title))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.item_job_company_title), withText(Datasets.SINGLE_JOB.company.title))).check(matches(isDisplayed()))
        onView(withId(R.id.item_job_chip_group)).check(matches(hasChildCount(2)))
        onView(withId(R.id.item_job_chip_group)).check(matches(atPositionChipGroup(0, withText(Datasets.SINGLE_JOB.getCategoryTitle(activityRule.activity)))))
        onView(withId(R.id.item_job_chip_group)).check(matches(atPositionChipGroup(1, withText(Datasets.SINGLE_JOB.getTypeTitle(activityRule.activity)))))
    }

    @Test
    fun testLoadingMultipleJob() {
        this.configureFragmentBeforeTest(mockQuery(Job::class.java, *Datasets.MULTIPLE_JOBS))

        onView(withId(R.id.fragment_job_list_refresh)).check(matches(not(isRefreshing())))
        onView(withId(R.id.fragment_job_list_rv)).check(matches((hasItemCount(30))))
    }

    @Test
    fun testLoadingNothing() {
        this.configureFragmentBeforeTest(mockQuery(Job::class.java))

        onView(withId(R.id.fragment_job_list_refresh)).check(matches(not(isRefreshing())))
        onView(withId(R.id.fragment_job_list_rv)).check(matches((hasItemCount(0))))
    }

    @Test
    fun testClickOnJob() {
        this.configureFragmentBeforeTest(mockQuery(Job::class.java, Datasets.SINGLE_JOB))

        onView(withId(R.id.fragment_job_list_rv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        intended(hasAction(Intent.ACTION_VIEW))
        intended(hasData(Datasets.SINGLE_JOB.url))
    }

    // ---

    /**
     * Create a new [JobListFragment]
     * and mock the [JobDao] with custom response [Query].
     * The fragment will be set inside a fake activity [SingleFragmentActivity]
     */
    private fun configureFragmentBeforeTest(query: Query) {
        val fragment = JobListFragment().apply {
            val firestore = mockk<FirebaseFirestore>()
            jobDao = object : JobDao(firestore) {
                override fun listApprovedJobs() = query
            }
        }
        activityRule.activity.setFragment(fragment)
    }
}

