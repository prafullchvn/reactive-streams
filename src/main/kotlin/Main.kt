package org.example

fun main() {
    val concreteObservable = ConcreteObservable<Int>(NumberAtIntervalProducer())
        .map {num -> num * 2  }
        .map { num -> num  + 5 }// Mono.just(source)


    val logger = object : Observer<Int> {
        override fun next(value: Int) {
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