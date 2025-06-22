package org.example

interface Observable<P> {
    fun subscribe(observer: Observer<P>): Subscription

    fun <R>  map(project: (P)->R): Observable<R>{
        val sourceObservable = this

        return object : Observable<R>{
            override fun subscribe(observer: Observer<R>): Subscription {
                val wrapperObserver = object : Observer<P> {
                    override fun next(value: P) {
                        val mappedValue = project(value)
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

/**
override fun subscribe(observer: Observer): Subscription {
val wrapperObserver = object : Observer {
override fun next(value: P) {
val mappedValue = project(value)
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

 */