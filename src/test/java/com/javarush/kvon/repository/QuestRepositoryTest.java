package com.javarush.kvon.repository;

import com.javarush.kvon.models.Node;
import com.javarush.kvon.models.Type;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestRepositoryTest {

    private static final String PATH_TO_JSON_FILE = QuestRepositoryTest.class.getResource("/quest_example.json").getPath();

    @Test
    public void checkQuestIsNotNull() {
        QuestRepository questRepository = new QuestRepository();
        assertNotNull(questRepository.getQuest());
    }

    @Test
    public void invokeReadStatesFromJSONFileCheckResult() {

        Node firstOption = new Node(1, Type.WINNING,
                "Ты принял вызов, молодец.", "Принять вызов", null, null);
        Node secondOption = new Node(2, Type.LOSING,
                "Ты отклонил вызов.", "Отклонить вызов", null, null);
        Node start = new Node(0, Type.INTERMEDIATE,
                "Ты потерял память?\nПринять вызов НЛО?", "", firstOption, secondOption);

        List<Node> nodes = new ArrayList<>();
        nodes.add(start);
        nodes.add(firstOption);
        nodes.add(secondOption);

        List<Node> nodesFromFile = null;
        try {
            nodesFromFile = (List<Node>) getReadStatesFromJSONFileMethod().invoke(null, PATH_TO_JSON_FILE);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            fail("Exceptions in code\n" + e.getMessage());
        }

        assertArrayEquals(nodes.toArray(), nodesFromFile.toArray());
    }

    private Method getReadStatesFromJSONFileMethod() throws NoSuchMethodException {
        Method method = QuestRepository.class.getDeclaredMethod("readStatesFromJSONFile", String.class);
        method.setAccessible(true);
        return method;
    }

}
