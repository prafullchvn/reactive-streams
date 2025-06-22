package org.example

class NumberProducer:Producer<Int> {
    override fun produce(observer: Observer<Int>) {
        val listOfNumbers = listOf(1, 2, 3, 4, 5)
        listOfNumbers.forEach { number ->
            observer.next(number)
        }
    }
}