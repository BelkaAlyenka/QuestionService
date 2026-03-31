package QuizService.service;

import QuizService.model.Question;
import java.util.Collection;

public interface QuestionService {

    void add(String question, String answer);

    void add(Question question);

    boolean remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
