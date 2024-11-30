package dev.ritobina.BookMyShow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketReqDTO {
    private List<Integer> showSeatIds;
    private int userId;

}
