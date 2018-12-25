package io.nobullshit.model

import java.util.*

data class Job(val title: String,
               val url: String,
               val category: Int,
               val type: Int,
               val company: Company,
               val createdAt: Date = Date(),
               val isApproved: Boolean = false)