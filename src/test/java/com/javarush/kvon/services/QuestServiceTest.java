package com.javarush.kvon.services;

import com.javarush.kvon.exceptions.IllegalOptionValueException;
import com.javarush.kvon.exceptions.NullStateOfQuestException;
import com.javarush.kvon.models.Node;
import com.javarush.kvon.models.Quest;
import com.javarush.kvon.models.Type;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class QuestServiceTest {

    private final Node firstOption = new Node(1, Type.WINNING,
            "Ты принял вызов, молодец.", "Принять вызов", null, null);
    private final Node secondOption = new Node(2, Type.LOSING,
            "Ты отклонил вызов.", "Отклонить вызов", null, null);
    private final Node start = new Node(0, Type.INTERMEDIATE,
            "Ты потерял память?\nПринять вызов НЛО?", "", firstOption, secondOption);
    Quest quest =  new Quest(start);

    @Test
    public void checkQuestIsNotNull() {

        QuestService questService = new QuestService();

        try {
            Field questField = getPrivateFieldFromQuestServiceClass("quest");
            Quest questFieldValue = (Quest) questField.get(questService);
            assertNotNull(questFieldValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exceptions in code\n" + e.getMessage());
        }

    }

    @Test
    public void invokePassToOptionCheckCurrentStateIsChanged() {

        QuestService questService = new QuestService();

        try {
            Field questFieldFromQuestService = getPrivateFieldFromQuestServiceClass("quest");
            questFieldFromQuestService.set(questService, quest);

            // after invoke method currentState of field quest must change on firstOption
            questService.passToOption(1);

            Field currentStateFieldFromQuest = getPrivateFieldFromQuestClass("currentState");
            Node currentStateFieldValueFromQuest = (Node) currentStateFieldFromQuest.get(quest);

            assertEquals(firstOption, currentStateFieldValueFromQuest);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exceptions in code\n" + e.getMessage());
        }

    }

    @Test
    public void invokePassToOptionCheckThrowingExceptionWhenParameterIsNotCorrect() {

        QuestService questService = new QuestService();
        assertDoesNotThrow(() -> questService.passToOption(1));
        assertDoesNotThrow(() -> questService.passToOption(2));
        assertThrows(IllegalOptionValueException.class, () -> questService.passToOption(3));

    }

    @Test
    public void invokePassToOptionMethodCheckThrowingExceptionWhenAttemptToSetStateOfQuestOnNull() {

        QuestService questService = new QuestService();
        questService.passToOption(2); //pass to final state of quest

        assertThrows(NullStateOfQuestException.class, () -> questService.passToOption(1));

    }

    private Field getPrivateFieldFromQuestServiceClass(String fieldName) throws NoSuchFieldException {
        Field questField = QuestService.class.getDeclaredField(fieldName);
        questField.setAccessible(true);
        return questField;
    }

    private Field getPrivateFieldFromQuestClass(String fieldName) throws NoSuchFieldException {
        Field questField = Quest.class.getDeclaredField(fieldName);
        questField.setAccessible(true);
        return questField;
    }
}
