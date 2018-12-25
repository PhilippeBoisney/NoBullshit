package io.nobullshit.nobullshit

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import io.nobullshit.nobullshit.base.TIBaseApplication

/**
 * Custom runner to disable dependency injection.
 */
class TIRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TIBaseApplication::class.java.name, context)
    }
}