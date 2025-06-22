package org.example

interface Producer<P> {
    fun produce(observer: Observer<P>)
}