package com.deloitte.moviebooking.booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class CreateBookingRequest {

    @NotBlank
    private String showId;

    @NotEmpty
    private List<String> seatIds;

    // getters
    public String getShowId() {
        return showId;
    }

    public List<String> getSeatIds() {
        return seatIds;
    }
}
