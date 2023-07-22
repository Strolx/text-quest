package com.javarush.kvon.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestTest {

    private final Node firstOption = new Node(2,
            State.WINNING, "Winning", "First option", null, null);
    private final Node secondOption = new Node(3,
            State.LOSING, "Losing", "Second option", null, null);

    private final int value = 1;
    private final State state = State.INTERMEDIATE;
    private final String proposal = "What the option will you choose?";
    private final String option = "";
    private final Node start = new Node(value, state, proposal, option, firstOption, secondOption);

    @Test
    public void createInstanceWhenParametersIsCorrect() {
        Quest quest = new Quest(start);
        assertEquals(quest, new Quest(start));
    }

    @Test
    public void createInstanceWhenParameterIsNullCheckThrowException() {
        assertThrows(NullPointerException.class, () -> new Quest(null));
    }

    @Test
    public void invokeIsQuestOver() {
        Quest quest = new Quest(start);
        boolean isQuestOver = start.getState() != State.INTERMEDIATE;
        assertEquals(isQuestOver, quest.IsQuestOver());

        quest = new Quest(firstOption);
        assertTrue(quest.IsQuestOver());
    }

    @Test
    public void invokeGetTextSecondOptionWhenFirstOptionOfNodeIsNotNull() {
        Quest quest = new Quest(start);
        assertEquals(firstOption.getOption(), quest.getTextFirstOption());
    }

    @Test
    public void invokeGetTextSecondOptionWhenFirstOptionOfNodeIsNull() {
        Quest quest = new Quest(firstOption);
        assertEquals("", quest.getTextFirstOption());
    }

    @Test
    public void invokeGetTextSecondOptionWhenSecondOptionOfNodeIsNotNull() {
        Quest quest = new Quest(start);
        assertEquals(secondOption.getOption(), quest.getTextSecondOption());
    }

    @Test
    public void invokeGetTextSecondOptionWhenSecondOptionOfNodeIsNull() {
        Quest quest = new Quest(secondOption);
        assertEquals("", quest.getTextSecondOption());
    }

}
