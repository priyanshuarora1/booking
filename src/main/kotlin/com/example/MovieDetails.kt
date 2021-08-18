package com.example

class MovieDetails(
    val dateTime: DateTime,
    val totalSeats: Int,
    val movie: Movie = Movie(),
    val NotBookedSeats: Int = totalSeats
)
{
    fun isSeatAvailable() {
        if (NotBookedSeats == 0)
            throw error("Opps!! Show is HouseFull")
    }
}

