package QuizService.controller;

import QuizService.model.Question;
import QuizService.service.JavaQuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping
    public Collection<Question> getAllQuestions() {
        return javaQuestionService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(
            @RequestParam(name = "question") String question,
            @RequestParam(name = "answer") String answer) {

        if (question == null || question.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Текст вопроса не может быть пустым");
        }
        if (answer == null || answer.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Текст ответа не может быть пустым");
        }

        javaQuestionService.add(question.trim(), answer.trim());
        return ResponseEntity.ok("Вопрос успешно добавлен");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeQuestion(
            @RequestParam("question") String question,
            @RequestParam("answer") String answer) {

        Question questionToRemove = new Question(question, answer);
        boolean removed = javaQuestionService.remove(questionToRemove);

        if (removed) {
            return ResponseEntity.ok("Вопрос успешно удалён");
        } else {
            return ResponseEntity.status(404).body("Вопрос не найден");
        }
    }
}
