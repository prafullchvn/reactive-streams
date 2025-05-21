package org.example

fun main() {
    val concreteObservable = ConcreteObservable(NumberProducer())
        .map<Int> {num -> num * 2  }
        .map<Int> { num -> num  + 5 }// Mono.just(source)


    val logger = object : Observer {
        override fun <P> next(value: P) {
            println("Next: $value")
        }

        override fun error(throwable: Throwable) {
            println("Error: ${throwable.message}")
        }

        override fun complete() {
            println("Complete")
        }
    }
    concreteObservable.subscribe(logger)
}