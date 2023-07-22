package com.javarush.kvon.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum State {
    INTERMEDIATE(""), WINNING("Победа"), LOSING("Поражение");
    private final String text;
}
