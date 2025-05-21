package org.example

interface Subscription {
    fun unsubscribe()

    fun isUnsubscribed(): Boolean
}