package com.exam.repo;

import com.exam.model.User;
import com.exam.model.exam.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.Set;

public interface ScoreRepository extends JpaRepository<Score,Long> {
    public Set<Score> findByuid(long uid);


}
