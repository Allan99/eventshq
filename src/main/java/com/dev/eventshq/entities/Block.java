package com.dev.eventshq.entities;

import com.dev.eventshq.dto.ActivityDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_block")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant start;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant finish;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(id, block.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
