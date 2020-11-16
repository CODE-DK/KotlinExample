package ru.skillbranch.kotlinexample.extensions

fun <E> List<E>.dropLastUntil(predicate: (E) -> Boolean): List<E> {
    val mutableList = this.asReversed().toMutableList()
    val iterator = mutableList.iterator()

    while (iterator.hasNext()) {
        val next = iterator.next()
        if (predicate.invoke(next)) {
            iterator.remove()
            break
        }
        iterator.remove()
    }
    return mutableList
}


