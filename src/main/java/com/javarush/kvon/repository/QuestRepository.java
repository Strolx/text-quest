package com.javarush.kvon.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.kvon.exceptions.IllegalDataFileException;
import com.javarush.kvon.models.Node;
import com.javarush.kvon.models.Quest;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Getter
@Log4j2
public class QuestRepository {

    @NonNull
    private final Quest quest;

    public QuestRepository() {
        String pathToQuestFile = getClass().getResource("/quest.json").getPath();
        List<Node> statesOfQuest = readStatesFromJSONFile(pathToQuestFile);
        Node start = statesOfQuest.get(0);
        quest = new Quest(start);
    }

    private static List<Node> readStatesFromJSONFile(@NonNull String pathToQuestFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        File questFile = new File(pathToQuestFile);
        try {
            List<Node> nodes = objectMapper.readValue(questFile, new TypeReference<>(){});
            log.info("Data from file read successfully");
            if (nodes.size() == 0) {
                log.error(IllegalDataFileException.MSG);
                throw new IllegalDataFileException();
            }
            return nodes;
        } catch (IOException e) {
            log.error("Can't read data from file.\n" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
