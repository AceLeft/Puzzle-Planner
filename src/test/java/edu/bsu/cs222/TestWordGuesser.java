package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestWordGuesser {
    WordGuesser wordGuesser = new WordGuesser("egg");

    public TestWordGuesser() throws IOException {
    }

    @Test
    public void testDoLettersMatch(){
        String kegResult = wordGuesser.doLettersMatch("keg");
        Assertions.assertEquals("-*g", kegResult);
        String eggResult = wordGuesser.doLettersMatch("egg");
        Assertions.assertEquals("egg",eggResult);
        String topResult = wordGuesser.doLettersMatch("top");
        Assertions.assertEquals("---",topResult);

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
