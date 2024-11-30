package dev.ritobina.BookMyShow.models;

import dev.ritobina.BookMyShow.models.constant.SeatStatus;
import dev.ritobina.BookMyShow.models.constant.SeatType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//@Table(name = "seats")  // explicitly define table name
public class Seat extends BaseModel {
    //@Column(name = "row_number")
    private int row_c;

    //@Column(name = "col_number")
    private int col_c;

    //@Column(name = "seat_number")
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type")
    private SeatType seatType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SeatStatus seatStatus;
}