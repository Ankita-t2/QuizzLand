package com.exam.service;

import com.exam.model.exam.Score;

import java.util.List;
import java.util.Set;

public interface ScoreService {

    public Score  addScore(Score score);
    public Set<Score> getScore(long uid);


   public void deleteScore(List<Long> scoreIds);
}
