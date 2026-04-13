package QuizService.service;

import QuizService.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> allQuestions = questionService.getAll();

        if (amount > allQuestions.size()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Запрошено больше вопросов, чем есть в сервисе"
            );
        }

        if (amount <= 0) {
            return Collections.emptyList();
        }

        List<Question> shuffledQuestions = new ArrayList<>(allQuestions);
        Collections.shuffle(shuffledQuestions);

        return new ArrayList<>(shuffledQuestions.subList(0, amount));
    }
}
