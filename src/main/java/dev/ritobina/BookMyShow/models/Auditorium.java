package dev.ritobina.BookMyShow.models;

import dev.ritobina.BookMyShow.models.constant.AuditoriumFeature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Auditorium extends BaseModel{
    private String name;
    private int capacity;
    @OneToMany
    @JoinColumn(name = "auditorium_id")
    private List<Seat> seats;
    @OneToMany
    @JoinColumn(name = "auditorium_id")
    private List<Show> shows;
    @ElementCollection // maintains relationship between audi and enum values
    @Enumerated(EnumType.STRING) // stores the enum constants as table with type string
    private List<AuditoriumFeature> auditoriumFeatures;
}
