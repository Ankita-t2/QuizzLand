package com.exam.model.exam;

import com.exam.model.User;

import javax.persistence.*;

@Entity()
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long scoreId;
    private long qid;

    private long uid;
    private double score;



    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Score(long scoreId) {
        this.scoreId = scoreId;
    }
    public Score(){

    }



    public Score(long scoreId, long qid, long uid, double score) {
        this.scoreId = scoreId;
        this.qid = qid;
        this.uid = uid;
        this.score = score;
    }

    public long getScoreId() {
        return scoreId;
    }

    public void setScoreId(long scoreId) {
        this.scoreId = scoreId;
    }

    public long getQid() {
        return qid;
    }

    public void setQid(long qid) {
        this.qid = qid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
