package org.example

interface  Observer<P> {
    fun next(value: P)

    fun error(throwable: Throwable)

    fun complete()
}