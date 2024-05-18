package com.exam.service.impl;

import com.exam.model.exam.Score;
import com.exam.repo.ScoreRepository;
import com.exam.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public Score addScore(Score score) {

        return this.scoreRepository.save(score);
    }

    @Override
    public Set<Score> getScore(long uid) {
        return  this.scoreRepository.findByuid(uid);
    }

    @Override
    public void deleteScore(List<Long> scoreIds) {
        for (long scoreId:scoreIds) {
            this.scoreRepository.deleteById(scoreId);
        }

    }


}
