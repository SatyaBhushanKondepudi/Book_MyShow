package com.satyabhushan.bookmyshow.services.Theater;

import com.satyabhushan.bookmyshow.exceptions.Theater.TheaterAlreadyExistsException;
import com.satyabhushan.bookmyshow.exceptions.Theater.TheaterNotFoundException;
import com.satyabhushan.bookmyshow.exceptions.User.UnauthorizedAccessException;
import com.satyabhushan.bookmyshow.models.Theater.Theater;
import com.satyabhushan.bookmyshow.repositories.ITheaterRepository;
import com.satyabhushan.bookmyshow.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheaterServiceImpl implements ITheaterService {
    @Autowired
    private ITheaterRepository iTheaterRepository;
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public Theater addTheater(Theater theater  , String email) throws UnauthorizedAccessException , TheaterAlreadyExistsException {
        checkAuthorization(email);
        Optional<Theater> theaterExists =  iTheaterRepository.findByTheaterName(theater.getTheaterName());
        if (theaterExists.isPresent()) {
            throw  new TheaterAlreadyExistsException("Theater already exists");
        }
        iTheaterRepository.save(theater);
        return theater;
    }

    @Override
    public Theater updateTheater(Theater theater , String email) throws TheaterNotFoundException, UnauthorizedAccessException {
        checkAuthorization(email);
        Theater findTheater = iTheaterRepository.findByTheaterName(theater.getTheaterName())
                .orElseThrow(() -> new TheaterNotFoundException("Theater not found"));

        if(!theater.getTheaterLocation().isEmpty()) {
            findTheater.setTheaterLocation(theater.getTheaterLocation());
        }
        if(!theater.getTheaterCity().isEmpty()) {
            findTheater.setTheaterCity(theater.getTheaterCity());
        }
        if(!theater.getTheaterState().isEmpty()) {
            findTheater.setTheaterState(theater.getTheaterState());
        }
        if(!theater.getTheaterCountry().isEmpty()) {
            findTheater.setTheaterCountry(theater.getTheaterCountry());
        }
        if(theater.getScreenNumber() != 0) {
            findTheater.setScreenNumber(theater.getScreenNumber());
        }
        iTheaterRepository.save(findTheater);
        return findTheater;
    }



    @Override
    public Theater deleteTheater(Long theaterId  , String email ) throws TheaterNotFoundException, UnauthorizedAccessException {
        checkAuthorization(email);
        Theater findTheater = iTheaterRepository.findById(theaterId)
                .orElseThrow(() -> new TheaterNotFoundException("Theater not found"));
        iTheaterRepository.delete(findTheater);
        return findTheater;
    }

    private boolean checkAuthorization(String email) throws UnauthorizedAccessException {
        String role = iUserRepository.findRoleByEmail(email);
        if (role == null || !role.equals("ADMIN")) {
            throw new UnauthorizedAccessException("You are not authorized to perform this action");
        }
        return true;
    }
}
