package com.stark.javalib

import kotlin.experimental.and

/**
 * @Description:
 * @Author: alex.luo
 * @CreateDate: 2022/11/14
 */
object Testkt {

    fun test() {
        val aArray = byteArrayOf(0x00, 0x01)
        val bArray = byteArrayOf(0x00, 0x01)
        if (aArray == bArray) {
            println("aArray == bArray")
        } else {
            println("aArray != bArray")
        }
    }
}

fun main() {
    val first = 0x81.toByte()
    if (first and 0x80.toByte() == 0.toByte()) {
        println("== first = $first")
    } else {
        println("!= first = $first")
    }
}