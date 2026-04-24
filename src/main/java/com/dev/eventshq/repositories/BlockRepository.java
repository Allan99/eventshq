package com.dev.eventshq.repositories;

import com.dev.eventshq.entities.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {
}
