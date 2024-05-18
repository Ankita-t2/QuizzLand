package com.exam.controller;


import com.exam.model.exam.Score;
import com.exam.service.impl.ScoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/score")
@CrossOrigin("*")
public class ScoreController {

    @Autowired
    private ScoreServiceImpl scoreservice;

    @PostMapping("/")
    public ResponseEntity<Score> addScore(@RequestBody Score score){
        return  ResponseEntity.ok(this.scoreservice.addScore(score));
    }

    @GetMapping("/getscore/{uid}")
    public ResponseEntity<?> getAllScore( @PathVariable("uid") long uid){
        return  ResponseEntity.ok(this.scoreservice.getScore(uid));
    }

    @DeleteMapping("/deleteScore")
    public void deleteScores(@RequestParam("scoreIds") List<Long> scoreIds) {
//        List<Long> ids = Arrays.stream(scoreIds.split(","))
//                .map(Long::parseLong) // Parsing string to long
//                .collect(Collectors.toList());
//        this.scoreservice.deleteScore(ids);

            this.scoreservice.deleteScore(scoreIds);

    }

}
