package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestWordGuesserGame {
    WordGuesserGame wordGuesserGame = new WordGuesserGame("egg");

    @ParameterizedTest
    /*
    MakeClueFromGuess() returns a clue from a given guess after checking it
    with the template word (in this case, "egg").
    If a letter from a guess is not in the template word, the corresponding
    clue letter for that index is a "-".
    If a letter from a guess is in the template word, but is not in the
    spot in which it appears in the guess, the corresponding clue letter for
    that index is a "*".
    If a letter from a guess is in the template word at
    the correct spot, the corresponding clue letter for that index is the
    correct letter.
     */
    @CsvSource({"keg, -*g", "egg, egg", "top, ---", "gop, *--"})
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
