package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class HintTest {

    @ParameterizedTest
    @EnumSource(value = Hint.class)
    public void testGetValue(String expected, Hint type){
        Assertions.assertEquals(expected, type.getValue());
    }
}
