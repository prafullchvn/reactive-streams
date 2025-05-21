package org.example

class SimpleSubscription: Subscription {
    private var isUnsubscribed = false
    override fun unsubscribe() {
        isUnsubscribed = true
    }

    override fun isUnsubscribed(): Boolean {
        return isUnsubscribed
    }
}