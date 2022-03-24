package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

public class    TestWordGuesser {
    WordGuesser wordGuesser = new WordGuesser("egg");

    public TestWordGuesser() throws IOException {
    }

    @ParameterizedTest
    @CsvSource({"keg, -*g","egg, egg","top, ---","gop, *--"})
    public void testMakeClueFromGuess(String guess, String expected){
        String result = wordGuesser.makeClueFromGuess(guess);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCreateNewTemplateWord() throws IOException {
        wordGuesser.createNewTemplateWord();
        Assertions.assertNotEquals("egg",wordGuesser.getTemplate());
        System.out.println("Word: " + wordGuesser.getTemplate());
        wordGuesser = new WordGuesser();
        Assertions.assertNotEquals("egg",wordGuesser.getTemplate());
    }
}
