package com.satyabhushan.bookmyshow.controllers.Admin;

import com.satyabhushan.bookmyshow.dtos.TheaterRequestDto;
import com.satyabhushan.bookmyshow.exceptions.Theater.TheaterAlreadyExistsException;
import com.satyabhushan.bookmyshow.exceptions.Theater.TheaterNotFoundException;
import com.satyabhushan.bookmyshow.exceptions.User.UnauthorizedAccessException;
import com.satyabhushan.bookmyshow.models.ENUMS.UserType;
import com.satyabhushan.bookmyshow.models.Theater.Theater;
import com.satyabhushan.bookmyshow.services.Theater.ITheaterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/admin/theater")
public class Admin_TheaterManagement {
    /*
    1. Add Theater
    2. Update Theater
    3. Delete Theater
     */

    private ITheaterService iTheaterService;
    public Admin_TheaterManagement(ITheaterService iTheaterService) {
        this.iTheaterService = iTheaterService;
    }

    @PostMapping("/addTheater")
    public ResponseEntity<?> addTheater(@RequestBody TheaterRequestDto theaterRequestDto) throws UnauthorizedAccessException, TheaterAlreadyExistsException {
            Theater theater = buildTheaterObject(theaterRequestDto);
            Theater savedTheater = iTheaterService.addTheater(theater , theaterRequestDto.getEmail());
            return new ResponseEntity<>(savedTheater, HttpStatus.CREATED);
    }

    private Theater buildTheaterObject(TheaterRequestDto theaterRequestDto) {
        return Theater.builder()
                .theaterName(theaterRequestDto.getTheaterName())
                .theaterLocation(theaterRequestDto.getTheaterLocation())
                .theaterCity(theaterRequestDto.getTheaterCity())
                .theaterState(theaterRequestDto.getTheaterState())
                .theaterCountry(theaterRequestDto.getTheaterCountry())
                .screenNumber(theaterRequestDto.getScreenNumber())
                .build();
    }

    @PutMapping("/updateTheater")
    public ResponseEntity<?> updateThearer(@RequestBody TheaterRequestDto theaterRequestDto) throws TheaterNotFoundException, UnauthorizedAccessException {
        Theater theater = iTheaterService.updateTheater(buildTheaterObject(theaterRequestDto) , theaterRequestDto.getEmail());
        return new ResponseEntity<>(theater.getTheaterName() + " updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteTheater/{theaterId}/{email}")
    public ResponseEntity<?> deleteTheater(@PathVariable Long theaterId , @PathVariable String email) throws TheaterNotFoundException, UnauthorizedAccessException {
        Theater theater = iTheaterService.deleteTheater(theaterId , email);
        return new ResponseEntity<>(theater.getTheaterName() + " deleted successfully ", HttpStatus.OK);
    }

}
