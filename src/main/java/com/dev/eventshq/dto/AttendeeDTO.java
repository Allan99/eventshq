package com.dev.eventshq.dto;

import com.dev.eventshq.entities.Activity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class AttendeeDTO {

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String email;

    @Getter
    private Set<ActivityDTO> activities = new HashSet<>();
}
