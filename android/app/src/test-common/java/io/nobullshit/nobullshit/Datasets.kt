package io.nobullshit.nobullshit

import io.nobullshit.nobullshit.model.Company
import io.nobullshit.nobullshit.model.Job

object Datasets {

    val SINGLE_JOB = Job(title = "My awesome Job",
        url = "https://www.google.com",
        category = Job.Category.ANDROID.value,
        type = Job.Type.FULL_TIME.value,
        company = Company("Google", "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_150x54dp.png"),
        approved = true)

    val MULTIPLE_JOBS: Array<Job> = Array(30) { SINGLE_JOB }
}