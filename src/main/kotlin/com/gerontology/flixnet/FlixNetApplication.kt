package com.gerontology.flixnet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class FlixNetApplication

fun main(args: Array<String>) {
    runApplication<FlixNetApplication>(*args)
}
