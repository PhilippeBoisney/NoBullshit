package io.nobullshit

import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.FirestoreOptions
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.freemarker.FreeMarker
import io.ktor.routing.*
import io.nobullshit.database.Database
import io.nobullshit.route.index
import io.nobullshit.route.styles
import io.nobullshit.route.submitJob

/*
* Entry Point of the application as defined in resources/application.conf
*/
fun Application.main() {
    val db = Database()
    configureApplication(db)
    environment.monitor.subscribe(ApplicationStopped) { db.close() }
}

/**
 * This function is called from the entry point and tests to configure an application
 * using the specified [db] [Database].
 */
fun Application.configureApplication(db: Database){
    install(DefaultHeaders)
    install(CallLogging)
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    registerRoutes(db)
}

/*
* Register all the routes available to the application.
* They are split in several methods and files, so it can scale for larger
* applications keeping a reasonable amount of lines per file.
*/
fun Application.registerRoutes(db: Database){
    routing {
        styles()
        index()
        submitJob(db)
    }
}