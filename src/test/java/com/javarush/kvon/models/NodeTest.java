package com.javarush.kvon.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NodeTest {

    private final int value = 1;
    private final Type type = Type.INTERMEDIATE;
    private final String proposal = "Question";
    private final String option = "Choose action";
    private final Node firstOption = null;
    private final Node secondOption = null;

    @Test
    public void createInstanceWhenParametersIsCorrect() {
        Node node = new Node(value, type, proposal, option, firstOption, secondOption);
        assertEquals(node, new Node(value, type, proposal, option, firstOption, secondOption));
    }

    @Test
    public void createInstanceWhenTypeIsNullCheckThrowException() {
        assertThrows(NullPointerException.class, () -> new Node(value, null, proposal, option, firstOption, secondOption));
    }

    @Test
    public void createInstanceWhenProposalIsNullCheckThrowException() {
        assertThrows(NullPointerException.class, () -> new Node(value, type, null, option, firstOption, secondOption));
    }

    @Test
    public void createInstanceWhenOptionIsNullCheckThrowException() {
        assertThrows(NullPointerException.class, () -> new Node(value, type, proposal, null, firstOption, secondOption));
    }
}
