package com.satyabhushan.bookmyshow.models.Show;

import com.satyabhushan.bookmyshow.models.Base;
import com.satyabhushan.bookmyshow.models.ENUMS.SeatType;
import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "Show_Seat")
public class ShowSeat extends Base {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int seatId;

    private int seatNumber;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private char seatRow;
    private double price;
    private boolean isBooked;


    @ManyToOne
    private Show show;
}
