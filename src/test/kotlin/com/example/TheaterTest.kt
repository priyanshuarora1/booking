package com.example

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class TheatreTest : StringSpec({

    "should book a ticket successfully and update the state"{
        val datetime = DateTime(ShowTime.Evening, LocalDate.now())
        val moviedetail = listOf(MovieDetails(datetime, 100, Movie("SherShah")))
        val theatre = Theatre(moviedetail)
        val (changedState, ticket) = theatre.bookSeat(datetime)
        ticket.ticketNumber shouldBe 100
        changedState.getNumberOfTicketsAvailable(datetime) shouldBe 99
    }
    "Book tickets for different Movie Shows"{
        val datetime = DateTime(ShowTime.Evening, LocalDate.now())
        val datetime2 = DateTime(ShowTime.Morning, LocalDate.now())
        val moviedetail = listOf(MovieDetails(datetime, 100, Movie("SherShah")),
            MovieDetails(datetime2,50, Movie("SherShah"))
        )
        val theatre = Theatre(moviedetail)
        val (changedState, ticket) = theatre.bookSeat(datetime)
        ticket.ticketNumber shouldBe 100

        val (changedState1, ticket1) = theatre.bookSeat(datetime2)
        ticket1.ticketNumber shouldBe 50
    }

    "Should throw error when show is full"{
        val datetime = DateTime(ShowTime.Evening, LocalDate.now())
        val moviedetail = listOf(MovieDetails(datetime, 1, Movie("SherShah")))
        val theatre = Theatre(moviedetail)

        val (newTheatreState, ticket) = theatre.bookSeat(datetime)

        val exception = shouldThrow<IllegalStateException> {
            newTheatreState.bookSeat(datetime)
        }
        exception.message shouldBe "Opps!! Show is HouseFull"
    }
    "should throw error for booking beyond 7 days"{
        val datetime = DateTime(ShowTime.Evening, LocalDate.now().plusDays(8))
        val moviedetail = MovieDetails(datetime, 100, Movie("SherShah"))
        val theatre = Theatre(listOf(moviedetail))
        val exception = shouldThrow<IllegalStateException> {
            theatre.bookSeat(datetime)
        }
        exception.message shouldBe "Tickets for this movie is not available for the selected date"    }
})
