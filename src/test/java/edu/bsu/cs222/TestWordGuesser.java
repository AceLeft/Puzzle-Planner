package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestWordGuesser {
    @Test
    public void testDoLettersMatch(){
        WordGuesser wordGuesser = new WordGuesser("egg");
        String kegResult = wordGuesser.doLettersMatch("keg");
        Assertions.assertEquals("-*g", kegResult);
        String eggResult = wordGuesser.doLettersMatch("egg");
        Assertions.assertEquals("egg",eggResult);
        String topResult = wordGuesser.doLettersMatch("top");
        Assertions.assertEquals("---",topResult);

    }
}
