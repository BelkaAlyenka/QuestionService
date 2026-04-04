package QuizService.controller;

import QuizService.model.Question;
import QuizService.service.ExaminerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public ResponseEntity<Set<Question>> getQuestions(@PathVariable int amount) {
        Collection<Question> questions = examinerService.getQuestions(amount);
        Set<Question> uniqueQuestions = Set.copyOf(questions);


        if (uniqueQuestions.size() != amount) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Set.of());
        }

        return ResponseEntity.ok(uniqueQuestions);
    }
}
