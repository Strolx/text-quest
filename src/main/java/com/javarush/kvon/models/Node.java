package com.javarush.kvon.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,
        property="value", scope=Node.class)
public class Node {

    private int value;
    @NonNull
    private Type type;
    @NonNull
    private String proposal;
    @NonNull
    private String option;
    private Node firstOption;
    private Node secondOption;

    private Node() {}
}
