package io.nobullshit.nobullshit.extension

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment

/**
 * Helper method to open an url [String]
 * with a [CustomTabsIntent]
 */
fun Fragment.openBrowser(url: String){
    try {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    } catch (exception: Exception) {

    }
}