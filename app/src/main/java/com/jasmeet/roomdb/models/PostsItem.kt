package com.jasmeet.roomdb.models

import java.io.Serializable


data class PostsItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
): Serializable {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}