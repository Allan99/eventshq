package com.dev.eventshq.dto;

import com.dev.eventshq.entities.Activity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(max = 225)
    @NotBlank(message = "Email cannot be empty")

    private String name;
    @Getter
    @Setter
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email not valid",
            regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Getter
    private Set<ActivityDTO> activities = new HashSet<>();
}
