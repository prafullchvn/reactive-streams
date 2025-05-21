package org.example

interface  Observer {
    fun<P> next(value: P)

    fun error(throwable: Throwable)

    fun complete()
}