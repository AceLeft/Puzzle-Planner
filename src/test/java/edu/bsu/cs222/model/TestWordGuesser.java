package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class    TestWordGuesser {
    WordGuesser wordGuesser = new WordGuesser("egg");

    @ParameterizedTest
    @CsvSource({"keg, -*g","egg, egg","top, ---","gop, *--"})
    public void testMakeClueFromGuess(String guess, String expected){
        String result = wordGuesser.makeClueFromGuess(guess);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCreateNewTemplateWord(){
        wordGuesser.createNewTemplateWord();
        Assertions.assertNotEquals("egg",wordGuesser.getTemplateWord());
        System.out.println("Word: " + wordGuesser.getTemplateWord());
        wordGuesser = new WordGuesser();
        Assertions.assertNotEquals("egg",wordGuesser.getTemplateWord());
    }
}
