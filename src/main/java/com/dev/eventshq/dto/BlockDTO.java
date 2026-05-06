package com.dev.eventshq.dto;

import com.dev.eventshq.entities.Activity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BlockDTO {

    private Long id;

    @NotBlank(message = "Field cannot be empty")
    private Instant start;
    @NotBlank(message = "Field cannot be empty")
    private Instant finish;

    private ActivityDTO activity;
}
