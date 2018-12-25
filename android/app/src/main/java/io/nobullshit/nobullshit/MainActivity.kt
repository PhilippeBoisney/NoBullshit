package io.nobullshit.nobullshit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.nobullshit.nobullshit.ui.joblist.JobListFragment
import javax.inject.Inject

/**
 *  A simple activity that holds the [JobListFragment] fragment
 */
class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.configureAndShowFragment()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

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
