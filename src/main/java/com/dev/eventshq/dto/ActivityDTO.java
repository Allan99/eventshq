package com.dev.eventshq.dto;

import com.dev.eventshq.entities.Attendee;
import com.dev.eventshq.entities.Block;
import com.dev.eventshq.entities.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActivityDTO {

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @NotBlank(message = "Name field cannot be empty")
    private String name;
    @Getter
    @Setter
    @NotBlank(message = "Description field cannot be empty")
    private String description;
    @Getter
    @Setter
    @NotBlank(message = "Price field cannot be empty")
    @Positive(message = "Price field has to be positive")
    private Double price;

    @Getter
    @Setter
    private Category category;

    @Getter
    private List<BlockDTO> blocks = new ArrayList<>();

    @Getter
    private Set<AttendeeDTO> attendees = new HashSet<>();
}
