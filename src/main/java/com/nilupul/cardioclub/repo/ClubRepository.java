package com.nilupul.cardioclub.repo;

import com.nilupul.cardioclub.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {

    Optional<Club> findByTitle(String title);
}
