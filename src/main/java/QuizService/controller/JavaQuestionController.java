package QuizService.controller;

import QuizService.model.Question;
import QuizService.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final QuestionService service;

    public JavaQuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<Question> getAllQuestions() {
        return service.getAll();
    }

    @PostMapping("/add")
    public Question addQuestion(
            @RequestParam("question") String question,
            @RequestParam("answer") String answer) {
        return service.add(question, answer);
    }

    @DeleteMapping("/remove")
    public Question removeQuestion(
            @RequestParam("question") String question,
            @RequestParam("answer") String answer) {
        Question newQuestion = new Question(question, answer);
        return service.remove(newQuestion);
    }
}
