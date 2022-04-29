package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class WordGuesserGameTest {

    private final WordGuesserGame wordGuesserGame = new WordGuesserGame("egg");

    private static Stream<Arguments> provideHintListForGuess() {
        return Stream.of(
                Arguments.of("keg", List.of(Hint.INCORRECT, Hint.SEMICORRECT, Hint.CORRECT)),
                Arguments.of("egg", List.of(Hint.CORRECT, Hint.CORRECT, Hint.CORRECT)),
                Arguments.of("top", List.of(Hint.INCORRECT, Hint.INCORRECT, Hint.INCORRECT)),
                Arguments.of("gop", List.of(Hint.SEMICORRECT, Hint.INCORRECT, Hint.INCORRECT))
        );
    }

    @ParameterizedTest
    @MethodSource({"provideHintListForGuess"})
    public void testMakeClueFromGuess(String guess, List<Hint> expected) {
        String result = wordGuesserGame.makeHintListFromGuess(guess);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCreateNewTemplateWord() {
        wordGuesserGame.createNewTemplateWord();
        Assertions.assertNotEquals("egg", wordGuesserGame.getTemplateWord());
    }
}
