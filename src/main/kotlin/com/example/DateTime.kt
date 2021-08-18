package com.example

import java.time.LocalDate

data class DateTime(val time:ShowTime,val date: LocalDate) {
    fun isDateValid(){
        if(date>LocalDate.now().plusDays(7))
            throw error("Tickets for this movie is not available for the selected date")
    }
}