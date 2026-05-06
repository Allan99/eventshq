package com.dev.eventshq.dto;

import com.dev.eventshq.entities.Activity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "Field cannot be empty")
    private String description;

    @Getter
    private List<ActivityDTO> activities = new ArrayList<>();
}
