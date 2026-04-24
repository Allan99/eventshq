package com.dev.eventshq.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_activity")
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @Getter
    @Setter
    private Category category;

    @OneToMany(mappedBy = "activity")
    @Getter
    private List<Block> blocks = new ArrayList<>();

    @ManyToMany(mappedBy = "activities")
    @Getter
    private Set<Attendee> attendees = new HashSet<>();
}
