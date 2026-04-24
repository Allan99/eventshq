package com.dev.eventshq.repositories;

import com.dev.eventshq.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
