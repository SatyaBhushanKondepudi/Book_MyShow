package com.satyabhushan.bookmyshow.models.Theater;

import com.satyabhushan.bookmyshow.models.Base;
import com.satyabhushan.bookmyshow.models.ENUMS.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "THEATER_SEATS")
public class TheaterSeat extends Base{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int seatId;
    private int seatNumber;
    private int seatPrice;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private char seatRow;
    @ManyToOne
    private Theater theater;
}

/*
TheaterSeat : Theater
One Theater can have multiple TheaterSeats
One TheaterSeat belongs to one Theater
 */
