package QuizService.service;

import QuizService.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public void add(String question, String answer) {
        add(new Question(question, answer));
    }

    @Override
    public void add(Question question) {
        questions.add(question);
    }

    @Override
    public boolean remove(Question question) {
        return questions.remove(question);
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
