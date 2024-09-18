package com.satyabhushan.bookmyshow.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketBookingController {
    /*
    1. Get the request from MovieTheaterController
    2. Calculate the price of the tickets based on the number of seats and the price of each seat
    3. Return the total price to the user
    4. Send an email to the user with the total price and the payment link
    5. Update the seats in the database
    6. Send the booking details to the user over email.
     */

}
