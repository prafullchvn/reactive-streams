package org.example

interface Observable {
    fun subscribe(observer: Observer): Subscription

    fun <P> map(project: (P)->P): Observable{
        val sourceObservable = this

        return object : Observable{
            override fun subscribe(observer: Observer): Subscription {
                val wrapperObserver = object : Observer {
                    override fun <R> next(value: R) {
                        val mappedValue = project(value as P)
                        println("in the observable of map next $mappedValue")
                        observer.next(mappedValue)
                    }

                    override fun error(throwable: Throwable) {
                        observer.error(throwable)
                    }

                    override fun complete() {
                        observer.complete()
                    }
                }
                return sourceObservable.subscribe(wrapperObserver)
            }
        }
    }

}