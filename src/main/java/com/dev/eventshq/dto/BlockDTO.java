package com.dev.eventshq.dto;

import com.dev.eventshq.entities.Activity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BlockDTO {

    private Long id;
    private Instant start;
    private Instant finish;

    private ActivityDTO activity;
}
