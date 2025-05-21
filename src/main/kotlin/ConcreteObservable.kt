package org.example

class ConcreteObservable(
    private val producer: Producer
): Observable {

    var observer: Observer? = null
    override fun subscribe(observer: Observer): Subscription {
        val subscription = SimpleSubscription()

        println("In the subscribe of concrete observable")

        /**
         * Wrapper observer to handle the unsubscribe logic
         */
        val wrapperObserver = object :Observer{

            override fun <P> next(value: P) {
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