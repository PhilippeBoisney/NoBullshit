package io.nobullshit.nobullshit.base

import android.app.Application

/**
 * We use a separate App for tests to prevent initializing dependency injection.
 *
 * See [io.nobullshit.nobullshit.TIRunner].
 */
class TIBaseApplication : Application()