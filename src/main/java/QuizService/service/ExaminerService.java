package QuizService.service;

import QuizService.model.Question;
import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
