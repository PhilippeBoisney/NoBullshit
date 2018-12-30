package io.nobullshit.nobullshit.ui.joblist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.google.firebase.firestore.*
import io.nobullshit.nobullshit.Datasets
import io.nobullshit.nobullshit.R
import io.nobullshit.nobullshit.db.dao.JobDao
import io.nobullshit.nobullshit.di.remoteDataSourceTestModule
import io.nobullshit.nobullshit.extension.getCategoryTitle
import io.nobullshit.nobullshit.extension.getTypeTitle
import io.nobullshit.nobullshit.model.Job
import io.nobullshit.nobullshit.testing.SingleFragmentActivity
import io.nobullshit.nobullshit.util.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.dsl.module.Module

/**
 * Instrumented tests for [JobListFragment]
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class TIJobList {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @After
    fun tearDown(){
        stopKoin()
    }

    @Test
    fun testLoadingSingleJob() {
        this.configureCustomDependencies(mockQuery(Job::class.java, Datasets.SINGLE_JOB))

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
        this.configureCustomDependencies(mockQuery(Job::class.java, *Datasets.MULTIPLE_JOBS))

        onView(withId(R.id.fragment_job_list_refresh)).check(matches(not(isRefreshing())))
        onView(withId(R.id.fragment_job_list_rv)).check(matches((hasItemCount(30))))
    }

    @Test
    fun testLoadingNothing() {
        this.configureCustomDependencies(mockQuery(Job::class.java))

        onView(withId(R.id.fragment_job_list_refresh)).check(matches(not(isRefreshing())))
        onView(withId(R.id.fragment_job_list_rv)).check(matches((hasItemCount(0))))
    }

    // ---

    /**
     * Configure custom [Module] for each [Test]
     * and provide a [JobDao] with custom mocked response [Query].
     *
     * The fragment will be set inside a fake activity [SingleFragmentActivity]
     */
    private fun configureCustomDependencies(query: Query) {
        loadKoinModules(remoteDataSourceTestModule(query))
        this.activityRule.activity.setFragment(JobListFragment())
    }
}

