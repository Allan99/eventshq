package com.dev.eventshq.dto;

import com.dev.eventshq.entities.Attendee;
import com.dev.eventshq.entities.Block;
import com.dev.eventshq.entities.Category;
import jakarta.persistence.*;
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
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Double price;

    @Getter
    @Setter
    private Category category;

    @Getter
    private List<BlockDTO> blocks = new ArrayList<>();

    @Getter
    private Set<AttendeeDTO> attendees = new HashSet<>();
}
