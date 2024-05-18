package com.exam.controller;

import com.exam.helper.UserNotFoundException;
import com.exam.model.User;
import com.exam.model.exam.Tutorial;
import com.exam.service.impl.TutorialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tutorial")
@CrossOrigin("*")
public class TutorialController {
    @Autowired
    private TutorialServiceImpl tutorialService;
    @PostMapping("/add-tutorial")
    public ResponseEntity<?> addTutorial(@RequestBody Tutorial tutorial ){




            return  ResponseEntity.ok(this.tutorialService.addTutorial(tutorial))  ;

    }

    @PutMapping("/update-tutorial")
    public ResponseEntity<?> updateTurorial(@RequestBody Tutorial tutorial){
        return  ResponseEntity.ok(this.tutorialService.updateTutorial(tutorial));

    }

    @GetMapping("/get-tut-list")
    public  ResponseEntity<List<Tutorial>> getTutorials(){
        return ResponseEntity.ok(this.tutorialService.getTutorials());
    }
    @GetMapping("/get-tutorial/{tutId}")
    public  ResponseEntity<Tutorial> getTutorial(@PathVariable("tutId") long tutId){
        return ResponseEntity.ok(this.tutorialService.getTutorial(tutId));
    }

    @DeleteMapping("/delete-tutorial/{tutId}")
    public  void deleteTutorial(@PathVariable("tutId")  long tutId){
        this.tutorialService.deleteTutorial(tutId);
    }



}
