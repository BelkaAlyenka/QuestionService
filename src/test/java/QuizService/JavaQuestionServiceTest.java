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
    public void testAddNewStrings() {
        String questionText = "Что такое int?";
        String answerText = "Это базовый тип данных";

        javaQuestionService.add(questionText, answerText);

        Collection<Question> allQuestions = javaQuestionService.getAll();
        assertEquals(1, allQuestions.size());

        Question addedQuestion = allQuestions.iterator().next();
        assertEquals(questionText, addedQuestion.getQuestion());
        assertEquals(answerText, addedQuestion.getAnswer());
    }

    @Test
    public void testAddNewQuestion() {
        Question question = new Question("Что такое интерфейс?", "Это контракт, который описывает поведение (набор методов), не реализуя его");

        javaQuestionService.add(question);

        Collection<Question> allQuestions = javaQuestionService.getAll();
        assertTrue(allQuestions.contains(question));
    }

    @Test
    public void testRemoveQuestion() {
        Question unnecessaryQuestion = new Question("Вопрос?", "Ответ");
        javaQuestionService.add(unnecessaryQuestion);

        boolean result = javaQuestionService.remove(unnecessaryQuestion);

        assertTrue(result);
        assertTrue(javaQuestionService.getAll().isEmpty());
    }

    @Test
    public void testGetAll() {
        Question firstQuestion = new Question("Какие ООП-концепты есть?", "Инкапсуляция, наследование, полиморфизм и абстракция");
        Question secondQuestion = new Question("Что такое локальные переменные?", "Локальные переменные находятся внутри методов или блоков кода и существуют только во время выполнения этих методов или блоков");
        javaQuestionService.add(firstQuestion);
        javaQuestionService.add(secondQuestion);

        Collection<Question> result = javaQuestionService.getAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(firstQuestion));
        assertTrue(result.contains(secondQuestion));

        result.clear();
        assertEquals(2, javaQuestionService.getAll().size());
    }

    @Test
    public void testGetRandomQuestion() {
        Question q1 = new Question("Вопрос 1", "Ответ 1");
        Question q2 = new Question("Вопрос 2", "Ответ 2");
        Question q3 = new Question("Вопрос 3", "Ответ 3");
        javaQuestionService.add(q1);
        javaQuestionService.add(q2);
        javaQuestionService.add(q3);

        for (int i = 0; i < 15; i++) {
            Question randomQuestion = javaQuestionService.getRandomQuestion();
            assertNotNull(randomQuestion);
            assertTrue(randomQuestion.equals(q1) || randomQuestion.equals(q2) || randomQuestion.equals(q3));
        }
    }
}
