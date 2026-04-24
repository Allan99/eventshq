package com.dev.eventshq.repositories;

import com.dev.eventshq.entities.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
}
