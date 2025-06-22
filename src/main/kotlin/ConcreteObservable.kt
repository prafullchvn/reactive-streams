package org.example

class ConcreteObservable<P>(
    private val producer: Producer<P>
): Observable<P> {

    private var observer: Observer<P>? = null
    override fun subscribe(observer: Observer<P>): Subscription {
        val subscription = SimpleSubscription()

        println("In the subscribe of concrete observable")

        /**
         * Wrapper observer to handle the unsubscribe logic
         */
        val wrapperObserver = object :Observer<P> {

            override fun  next(value: P) {
               if (isUnsubscribed()){
                   observer.next(value)
               }
            }

            override fun error(throwable: Throwable) {
                observer.error(throwable)
            }

            override fun complete() {
                observer.complete()
                subscription.unsubscribe()
            }

        }

        producer.produce(wrapperObserver)
        return subscription
    }

    private fun unsubscribe() {
        observer = null
    }
    private fun isUnsubscribed(): Boolean {
        return observer == null
    }

}