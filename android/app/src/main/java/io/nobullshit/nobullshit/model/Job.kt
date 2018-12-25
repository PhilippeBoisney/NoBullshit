package io.nobullshit.nobullshit.model

import java.util.*

/**
 * Represents a [Job]
 */
data class Job(val title: String = "",
               val url: String = "",
               val category: Int = Category.UNKNOWN.value,
               val type: Int = Type.UNKNOWN.value,
               val company: Company = Company("", ""),
               val createdAt: Date = Date(),
               val approved: Boolean = false) {

    enum class Category(val value: Int){
        ANDROID(1),
        UNKNOWN(-1)
    }

    enum class Type(val value: Int){
        FULL_TIME(1),
        PART_TIME(2),
        CONTRACT(3),
        INTERN(4),
        UNKNOWN(-1)
    }
}

