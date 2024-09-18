package com.satyabhushan.bookmyshow.models.Show;

import com.satyabhushan.bookmyshow.models.Base;
import com.satyabhushan.bookmyshow.models.ENUMS.SeatType;
import com.satyabhushan.bookmyshow.models.Theater.TheaterSeat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.security.Timestamp;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Ticket extends Base {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int ticketId;
    private double totalPrice;
    private int numberOfSeats;
    private String showName;
    private String movieName;
    private String theaterName;
    private String theaterCity;

    @CurrentTimestamp
    private Timestamp bookingTime;

    private Time showTime;
    private Date showDate;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private char seatRow;
    @OneToMany
    private List<TheaterSeat> seatNumber;
}
