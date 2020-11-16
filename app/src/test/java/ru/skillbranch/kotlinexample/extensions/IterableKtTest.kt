package ru.skillbranch.kotlinexample.extensions

import org.junit.Test

import org.junit.Assert.*

class IterableKtTest {

    @Test
    fun dropLastUntil() {
        println(listOf(1, 2, 3).dropLastUntil { it == 2 })
        println("House Nymeros Martell of Sunspear".split(" ").dropLastUntil { it == "of" })
    }
}