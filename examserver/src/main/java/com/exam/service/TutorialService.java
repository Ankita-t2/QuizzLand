package com.exam.service;

import com.exam.model.exam.Tutorial;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface TutorialService {
    public Tutorial addTutorial(Tutorial tutorial);
    public  Tutorial getTutorial(long tutId);
    public List<Tutorial> getTutorials();
    public  Tutorial updateTutorial(Tutorial tutorial);

    public  void deleteTutorial(long tutId);
}
