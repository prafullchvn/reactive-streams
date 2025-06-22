package org.example

class NumberAtIntervalProducer: Producer<Int> {
    override fun produce(observer: Observer<Int>) {
       val listOfNumber = listOf(1, 2, 3, 4, 5)
        listOfNumber.forEach { number ->
            Thread.sleep(1000) // Simulate delay
            println("Producing number: $number")
            observer.next(number)
        }
        observer.complete()
    }
}