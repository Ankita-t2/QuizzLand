package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    //add Quiz
    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }
    //update Quiz
    @PutMapping("/")
    public  ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
        return  ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    //get Quiz

    @GetMapping("/")
    public ResponseEntity<?> getQuizzes(){
        return  ResponseEntity.ok(this.quizService.getQuizzes());
    }

    //get single quiz
    @GetMapping("/{qid}")
    public Quiz  getquiz(@PathVariable("qid")Long qid){
        return  this.quizService.getQuiz(qid);
    }

    //delete quiz
//    @DeleteMapping("/{qId}")
//    public void deleteQuiz(@PathVariable("qId") String qId){
//        try {
//            Long quizId = Long.parseLong(qId);
//            this.quizService.deleteQuiz(quizId);
//           // return ResponseEntity.ok("Quiz with ID " + quizId + " deleted successfully.");
//        } catch (NumberFormatException e) {
//            // Handle the case where qId is not a valid Long
//
//
//           // return ResponseEntity.badRequest().body("Invalid qId parameter. It should be a valid Long.");
//        }
//    }


    //
    @DeleteMapping("/{qId}")
    public void deleteQuiz(@PathVariable("qId") String qId) {
        try {
            Long quizId = Long.parseLong(qId);
            this.quizService.deleteQuiz(quizId);
           // return ResponseEntity.ok("Quiz with ID " + quizId + " deleted successfully.");
        } catch (NumberFormatException e) {
            // Handle the case where qId is not a valid Long
            System.out.println("number format exception");
           // return ResponseEntity.badRequest().body("Invalid qId parameter. It should be a valid Long.");
        }
    }
    @GetMapping("/category/{cid}")
    public List<Quiz> getQuzzesOfCategory(@PathVariable("cid")long cid){
        Category category = new Category();
        category.setCid(cid);
        return this.quizService.getQuizzesOfCategory(category);
    }

    //get active quizzes
    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes(){
        return this.quizService.getActiveQuizzes();
    }

    //get active quizzes of Category
    @GetMapping("/category/active/{cid}")
    public List<Quiz> getActiveQuizzesOfCategory(@PathVariable("cid")Long cid){
        Category category = new Category();
        category.setCid(cid);
        return this.quizService.getActiveQuizzesOfCategory(category);
    }


}
