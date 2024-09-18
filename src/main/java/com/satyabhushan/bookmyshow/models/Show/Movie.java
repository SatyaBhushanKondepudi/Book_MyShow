package com.satyabhushan.bookmyshow.models.Show;

import com.satyabhushan.bookmyshow.models.Base;
import com.satyabhushan.bookmyshow.models.ENUMS.Genre;
import com.satyabhushan.bookmyshow.models.Theater.Theater;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends Base {
    @Column(nullable = false)
    private String movieName;
    private Duration duration;
    @Column(scale = 5)
    private double rating;
    private Date releaseDate;
    private String language;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> shows;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Theater> theaters;

}
