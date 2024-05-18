package com.exam.service.impl;

import com.exam.model.exam.Tutorial;
import com.exam.repo.TutorialRepository;
import com.exam.service.TutorialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.List;
import java.util.Set;

@Service
public class TutorialServiceImpl implements TutorialService {


    @Autowired
    private TutorialRepository tutorialRepository;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Tutorial addTutorial(Tutorial tutorial) {

        return tutorialRepository.save(tutorial);

    }



    @Override
    public Tutorial getTutorial(long tutId) {
        return tutorialRepository.findByTutId(tutId);
    }

    @Override
    public List<Tutorial> getTutorials() {
        return tutorialRepository.findAll();
    }

    @Override
    public Tutorial updateTutorial(Tutorial tutorial) {



        return this.tutorialRepository.save(tutorial);
    }

    @Override
    public void deleteTutorial(long tutId) {
        this.tutorialRepository.deleteById(tutId);

    }
}
