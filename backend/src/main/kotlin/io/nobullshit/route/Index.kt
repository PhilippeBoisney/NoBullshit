package io.nobullshit.route

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.nobullshit.database.Database
import java.util.logging.Logger

const val PATH_INDEX = "/"

fun Route.index() {
    get(PATH_INDEX) {
        call.respond(FreeMarkerContent("index.ftl", null))
    }
}

