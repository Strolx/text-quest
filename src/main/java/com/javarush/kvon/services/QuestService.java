package com.javarush.kvon.services;

import com.javarush.kvon.exceptions.IllegalOptionValueException;
import com.javarush.kvon.exceptions.NullStateOfQuestException;
import com.javarush.kvon.models.Node;
import com.javarush.kvon.models.Quest;
import com.javarush.kvon.models.State;
import com.javarush.kvon.repository.QuestRepository;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class QuestService {

    @NonNull
    private final Quest quest;

    public QuestService() {
        QuestRepository questRepository = new QuestRepository();
        this.quest = questRepository.getQuest();
    }

    public boolean isQuestOver() {
        return quest.IsQuestOver();
    }

    public String getProposal() {
        return quest.getProposal().replace("\n", "<br>");
    }

    public String getTextFirstOption() {
        return quest.getTextFirstOption();
    }

    public String getTextSecondOption() {
        return quest.getTextSecondOption();
    }

    public void passToOption(int option) {
        switch (option) {
            case 1 -> {
                Node firstOption = quest.getFirstOption();
                if (firstOption == null) {
                    log.error("Attempt to set state of quest on null\n" + NullStateOfQuestException.MSG);
                    throw new NullStateOfQuestException();
                }
                quest.setCurrentState(firstOption);
            }
            case 2 -> {
                Node secondOption = quest.getSecondOption();
                if (secondOption == null) {
                    log.error("Attempt to set state of quest on null\n" + NullStateOfQuestException.MSG);
                    throw new NullStateOfQuestException();
                }
                quest.setCurrentState(secondOption);
            }
            default -> {
                log.error("Received value: " + option + "\n" + IllegalOptionValueException.MSG);
                throw new IllegalOptionValueException();
            }
        }
    }

    public String getResultOfQuest() {
        return quest.getStateOfQuest().getText();
    }

    public State getStateOfQuest() {
        return quest.getStateOfQuest();
    }

}
