package io.nobullshit.nobullshit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.nobullshit.nobullshit.ui.joblist.JobListFragment

/**
 *  A simple activity that holds the [JobListFragment] fragment
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.configureAndShowFragment()
    }

    // ---

    private fun configureAndShowFragment() {
        var fragment = supportFragmentManager.findFragmentById(R.id.activity_main_container) as JobListFragment?
        if (fragment == null) {
            fragment = JobListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.activity_main_container, fragment)
                .commit()
        }
    }
}
