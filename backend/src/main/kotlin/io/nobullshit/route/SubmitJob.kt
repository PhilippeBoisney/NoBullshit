package io.nobullshit.route

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.Parameters
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.nobullshit.database.Database
import io.nobullshit.model.Company
import io.nobullshit.model.Job
import java.util.logging.Logger

private const val PATH_SUBMIT_JOB = "/submit"

fun Route.submitJob(db: Database) {

    /**
     * A GET request returns a page with a form to post a new [Job]
     */
    get(PATH_SUBMIT_JOB) {
        call.respond(FreeMarkerContent("submit-job.ftl", null))
    }

    /**
     * A POST request that actually tries to create a new [Job].
     * On success it creates the new [Job] in Firestore and shows a success alert.
     * On error it shows an error alert.
     */
    post(PATH_SUBMIT_JOB) {
        try {
            val post = call.receive<Parameters>()
            val jobTitle = post["jobTitle"]!!
            val jobUrl = post["jobUrl"]!!
            val jobCategory = post["jobCategory"]!!.toInt()
            val jobType = post["jobType"]!!.toInt()
            val companyTitle = post["companyTitle"]!!
            val companyLogoUrl = post["companyLogoUrl"]!!

            val jobToCreate = Job(jobTitle, jobUrl, jobCategory, jobType, Company(companyTitle, companyLogoUrl))
            db.jobDao.createJob(jobToCreate)
            call.respond(FreeMarkerContent("submit-job.ftl", mapOf("success" to "The job was successfully created !")))
        } catch (e: Exception) {
            call.respond(FreeMarkerContent("submit-job.ftl", mapOf("error" to "An error happened during job creation...")))
        }
    }
}