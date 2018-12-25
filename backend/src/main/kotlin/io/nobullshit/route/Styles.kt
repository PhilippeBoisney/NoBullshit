package io.nobullshit.route

import io.ktor.application.call
import io.ktor.http.content.resolveResource
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

private const val PATH_STYLES_MAIN = "/styles/main.css"

/**
 * Register the styles route (/styles/main.css)
 */
fun Route.styles() {
    /**
     * On a GET request to the styles route, it returns the `nobullshit.css` file from the resources.
     */
    get(PATH_STYLES_MAIN) {
        call.respond(call.resolveResource("nobullshit.css")!!)
    }
}