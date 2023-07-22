package com.javarush.kvon.models;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Setter
@EqualsAndHashCode
@Log4j2
public class Quest {

    @NonNull
    private Node currentState;

    public Quest(@NonNull Node currentState) {
        this.currentState = currentState;
        log.info("New quest is created");
    }

    public boolean IsQuestOver() {
        return !State.INTERMEDIATE.equals(currentState.getState());
    }

    public String getProposal() {
        return currentState.getProposal();
    }

    public String getTextFirstOption() {
        Node firstOption = currentState.getFirstOption();
        if (firstOption == null) {
            log.warn("Attempt to get option from final state of quest.");
            return "";
        }
        return firstOption.getOption();
    }

    public String getTextSecondOption() {
        Node secondOption = currentState.getSecondOption();
        if (secondOption == null) {
            log.warn("Attempt to get option from final state of quest.");
            return "";
        }
        return currentState.getSecondOption().getOption();
    }

    public Node getFirstOption() {
        return currentState.getFirstOption();
    }

    public Node getSecondOption() {
        return currentState.getSecondOption();
    }

    public State getStateOfQuest() {
        return currentState.getState();
    }

}
