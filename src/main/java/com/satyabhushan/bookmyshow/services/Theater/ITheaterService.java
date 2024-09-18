package com.satyabhushan.bookmyshow.services.Theater;

import com.satyabhushan.bookmyshow.exceptions.Theater.TheaterAlreadyExistsException;
import com.satyabhushan.bookmyshow.exceptions.Theater.TheaterNotFoundException;
import com.satyabhushan.bookmyshow.exceptions.User.UnauthorizedAccessException;
import com.satyabhushan.bookmyshow.models.Theater.Theater;


public interface ITheaterService {
    Theater addTheater(Theater theater , String email) throws UnauthorizedAccessException , TheaterAlreadyExistsException;

    Theater updateTheater(Theater theater , String email) throws UnauthorizedAccessException , TheaterNotFoundException;

    Theater deleteTheater(Long theaterId , String email) throws UnauthorizedAccessException , TheaterNotFoundException;
}
