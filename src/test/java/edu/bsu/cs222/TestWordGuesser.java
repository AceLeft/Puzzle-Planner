package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestWordGuesser {
    WordGuesser wordGuesser = new WordGuesser("egg");
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
    public void testCreateNewTemplateWord(){
        wordGuesser.createNewTemplateWord();
        Assertions.assertNotEquals("egg",wordGuesser.getTemplate());
    }
}
