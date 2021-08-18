package com.example

class Movie(private var name: String="") {

    fun getName() = name

    fun setName(name: String) {
        this.name = name
    }

}