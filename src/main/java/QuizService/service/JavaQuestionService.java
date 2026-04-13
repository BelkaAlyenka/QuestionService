package QuizService.service;

import QuizService.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();


    @Override
    public Question add(String questionText, String answerText) {
        Question newQuestion = new Question(questionText, answerText);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Вопрос не может быть null");
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        } else {
            return null;
        }
    }

    @Override
    public Collection<Question> getAll() {
        return new HashSet<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        int size = questions.size();
        if (size == 0) {
            return null;
        }
        List<Question> questionsList = new ArrayList<>(questions);
        int randomIndex = random.nextInt(size);
        return questionsList.get(randomIndex);
    }
}
