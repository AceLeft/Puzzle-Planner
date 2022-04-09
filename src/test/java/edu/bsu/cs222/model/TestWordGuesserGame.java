package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestWordGuesserGame {
    WordGuesserGame wordGuesserGame = new WordGuesserGame("egg");

    @ParameterizedTest
    @CsvSource({
            "keg, -*g", //an incorrect, semicorrect, and correct case
            "egg, egg", //a fully correctly case
            "top, ---", //a fully incorrect case
            "gop, *--"  //a semicorrect and incorrect case
    })
    public void testMakeClueFromGuess(String guess, String expected) {
        String result = wordGuesserGame.makeClueFromGuess(guess);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCreateNewTemplateWord() {
        wordGuesserGame.createNewTemplateWord();
        Assertions.assertNotEquals("egg", wordGuesserGame.getTemplateWord());
    }
}
