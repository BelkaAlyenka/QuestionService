package QuizService;

import QuizService.model.Question;
import QuizService.service.ExaminerServiceImpl;
import QuizService.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    public void getZeroQuestionsTest() {
        when(questionService.getAll()).thenReturn(
                List.of(new Question("Нет вопроса", "Нет ответа" ))
        );

        Collection<Question> result = examinerService.getQuestions(0);

        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
    }

    @Test
    public void getCorrectQuestionsTest() {
        Question q1 = new Question("Вопрос 1", "Ответ 1");
        Question q2 = new Question("Вопрос 2", "Ответ 2");
        Question q3 = new Question("Вопрос 3", "Ответ 3");
        List<Question> allQuestions = Arrays.asList(q1, q2, q3);
        when(questionService.getAll()).thenReturn(allQuestions);

        Collection<Question> result = examinerService.getQuestions(2);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(Arrays.asList(q1, q2)) || result.containsAll(Arrays.asList(q1, q3)) || result.containsAll(Arrays.asList(q2, q3)));
    }

    @Test
    void getBadRequestTest() {
        when(questionService.getAll()).thenReturn(
                Arrays.asList(
                        new Question("Какие ООП-концепты есть?", "Инкапсуляция, наследование, полиморфизм и абстракция"),
                        new Question("Что такое локальные переменные?", "Локальные переменные находятся внутри методов или блоков кода и существуют только во время выполнения этих методов или блоков")
                )
        );

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> examinerService.getQuestions(3)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertTrue(exception.getMessage().contains("Запрошено больше вопросов, чем есть в сервисе"));
    }

}
