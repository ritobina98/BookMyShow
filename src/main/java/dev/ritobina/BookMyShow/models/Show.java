package dev.ritobina.BookMyShow.models;

import dev.ritobina.BookMyShow.models.constant.ShowStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shows")
public class Show extends BaseModel{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne
    private Movie movie;
    private String language;
    @ManyToOne
    private Auditorium auditorium;
    @Enumerated(EnumType.STRING)
    private ShowStatus showStatus;
    @OneToMany
    @JoinColumn(name = "show_id")
    private List<ShowSeat> showSeats;
}
