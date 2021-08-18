package com.example

data class Ticket(val ticketNumber: Int, val dateTime: DateTime)

enum class ShowTime{
    Morning,
    Evening,
    Night
}
class Theatre(private val shows: List<MovieDetails>) {

    fun bookSeat(dateTime: DateTime): Pair<Theatre, Ticket> {
        val movie = shows.first { it.dateTime == dateTime }

        dateTime.isDateValid()
        movie.isSeatAvailable()

        val ticket = Ticket(movie.NotBookedSeats, dateTime)
        val copyOfListOfMovieShows = updateMovieDetails(movie, dateTime)
        val newTheatreState = Theatre(copyOfListOfMovieShows)
        return Pair(newTheatreState, ticket)
    }

    fun getNumberOfTicketsAvailable(timeStamp: DateTime) =
        shows.first { it.dateTime == timeStamp }.NotBookedSeats


    private fun updateMovieDetails(movieDetails: MovieDetails, dateTime: DateTime): List<MovieDetails> {
        val copyOfshows = shows.toMutableList()

        copyOfshows.remove(movieDetails)
        copyOfshows.add(
            MovieDetails(
                dateTime,
                movieDetails.totalSeats,
                movieDetails.movie,
                movieDetails.NotBookedSeats - 1
            )
        )

        return copyOfshows.toList()
    }
}
