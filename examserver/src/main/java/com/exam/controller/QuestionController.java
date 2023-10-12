package com.exam.controller;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        System.out.println("add question");
        return  ResponseEntity.ok(this.questionService.addQuestion(question));

    }

    //update question
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return  ResponseEntity.ok(this.questionService.updateQuestion(question));

    }
    //get all question of any  quiz
    @GetMapping("/quiz/{qid}")
    public  ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid){
////        Quiz quiz = new Quiz();
////        quiz.setQid(qid);
////
////        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
////        return  ResponseEntity.ok(questionsOfQuiz);
      Quiz quiz = this.quizService.getQuiz(qid);


        Set<Question> questions = quiz.getQuestions();
        List<Question> list = new ArrayList(questions);

        String numberOfQuestionsString = quiz.getNumberOfQuestions();
        int numberOfQuestions = 0; // Initialize to a default value
        list.forEach((q)->{
            q.setAnswer("");
        });
    // Check if numberOfQuestionsString is a valid integer
        if (numberOfQuestionsString != null && numberOfQuestionsString.matches("\\d+")) {
            numberOfQuestions = Integer.parseInt(numberOfQuestionsString);
        }

        if (list.size() > numberOfQuestions) {
            list.subList(0, numberOfQuestions);
        }

        Collections.shuffle(list);

        return ResponseEntity.ok(list);


    }

    @GetMapping("/quiz/all/{qid}")
    public  ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid){
        Quiz quiz = new Quiz();
        quiz.setQid(qid);

        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
        return  ResponseEntity.ok(questionsOfQuiz);



    }

    //get Single question

    @GetMapping("/{quesId}")
    public  Question getQuestion(@PathVariable("quesId")Long quesId){
        return  this.questionService.getQuestion(quesId);
    }

    //delete Question

    @DeleteMapping("/{quesId}")
    public void deleteQuestion(@PathVariable("quesId")Long quesId){
        this.questionService.deleteQuestion(quesId);
    }


    //eval quiz
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
       double marksGot = 0;
       Integer correctAnswer = 0;
        Integer Attempted = 0;
        System.out.println(questions);
       for (Question q:questions){
            Question question = this.questionService.get(q.getQuesId());
            if (question.getAnswer().equals(q.getGivenAnswer())){
                //correct
                correctAnswer++;

                double markSingle =Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
                       // this.g[0].quiz.maxMarks / this.questions.length;
                    marksGot += markSingle;
            }
           if (q.getGivenAnswer()!=null) {
                      Attempted++;
           }

           };

        Map<String, Object> map = Map.of("marksGot",marksGot,"correctAnswer",correctAnswer,"Attempted",Attempted);
        return  ResponseEntity.ok(map);

    }
}
