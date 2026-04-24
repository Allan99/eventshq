package com.dev.eventshq.repositories;

import com.dev.eventshq.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
