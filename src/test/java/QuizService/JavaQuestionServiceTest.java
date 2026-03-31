package QuizService;

import QuizService.model.Question;
import QuizService.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    public void setUp() {
        javaQuestionService = new JavaQuestionService();
    }
    @Test
    public void testAddNewQuestion() {
        String questionText = "Что такое int?";
        String answerText = "Это базовый тип данных";

        javaQuestionService.add(questionText, answerText);

        Collection<Question> allQuestions = javaQuestionService.getAll();
        assertEquals(1, allQuestions.size());

        Question addedQuestion = allQuestions.iterator().next();
        assertEquals(questionText, addedQuestion.getQuestion());
        assertEquals(answerText, addedQuestion.getAnswer());

    }
}
