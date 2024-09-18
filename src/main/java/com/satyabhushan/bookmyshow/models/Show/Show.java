package com.satyabhushan.bookmyshow.models.Show;

import com.satyabhushan.bookmyshow.models.Base;
import com.satyabhushan.bookmyshow.models.ENUMS.ShowName;
import com.satyabhushan.bookmyshow.models.Theater.Theater;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Shows")
public class Show extends Base {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int showId;

    @Enumerated(EnumType.STRING)
    private ShowName showName;

    private Timestamp showDateTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie ;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<ShowSeat> showSeats;




}
