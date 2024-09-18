package com.satyabhushan.bookmyshow.models.Theater;

import com.satyabhushan.bookmyshow.models.Base;
import com.satyabhushan.bookmyshow.models.ENUMS.SeatType;
import com.satyabhushan.bookmyshow.models.Show.Movie;
import com.satyabhushan.bookmyshow.models.Show.Show;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theater extends Base {
    private String theaterName;
    @Column(nullable = false)
    private String theaterLocation;
    private String theaterCity;
    private String theaterState;
    private String theaterCountry;
    private int screenNumber;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @OneToMany(mappedBy = "theater" , cascade = CascadeType.ALL)
    private List<TheaterSeat> theaterSeatList ;

    @OneToMany(mappedBy = "theater" , cascade = CascadeType.ALL)
    private List<Show> theaterShowsList;
}
