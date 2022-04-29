package edu.bsu.cs222.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordGuesserGame {
    private String templateWord;
    private final WordGuesserWordList wordList = new WordGuesserWordList();
    private ArrayList<String> templateWordLetters;
    private ArrayList<String> guessLetters;

    public WordGuesserGame(String word) {
        templateWord = word;
    }

    public WordGuesserGame() {
        createNewTemplateWord();
    }

    public void createNewTemplateWord() {
        int randomNumber = (int) (Math.random() * wordList.getWordListLength());
        templateWord = wordList.getWordAt(randomNumber);
    }

    public String getTemplateWord() {
        return templateWord;
    }

    public boolean matchesTemplate(String guess) {
        return guess.equals(templateWord);
    }

    public List<Hint> makeHintListFromGuess(String guess) {
        guess = guess.toLowerCase();
        if (guess.length() != templateWord.length()) {
            //if guess is not correct length, do not return a hint
            return new ArrayList<>();
        }
        templateWordLetters = makeListOfLetters(templateWord);
        guessLetters = makeListOfLetters(guess);
        Hint[] hintValues = new Hint[templateWord.length()];
        findCorrectLetters(hintValues);
        findIncorrectAndSemiCorrectLetters(hintValues);
        return Arrays.asList(hintValues);
    }

    private ArrayList<String> makeListOfLetters(String word) {
        ArrayList<String> wordLetters = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            String wordLetter = String.valueOf(word.charAt(i));
            wordLetters.add(wordLetter);
        }
        return wordLetters;
    }

    private void findCorrectLetters(Hint[] clueLetters) {
        String curGuessLetter, curTemplateWordLetter;
        for (int i = 0; i < templateWordLetters.size(); i++) {
            curGuessLetter = guessLetters.get(i);
            curTemplateWordLetter = templateWordLetters.get(i);
            if (curTemplateWordLetter.equals(curGuessLetter)) {
                clueLetters[i] = Hint.CORRECT;
                //"remove" the letter, so it cannot be checked again
                templateWordLetters.set(i, "");
                guessLetters.set(i, "");
            }
        }
    }

    private void findIncorrectAndSemiCorrectLetters(Hint[] hintLetters) {
        int j = 0;
        int index;
        boolean letterCheckedPreviously;
        for (String letter : guessLetters) {
            letterCheckedPreviously = letter.equals("");
            if (templateWordLetters.contains(letter) && !letterCheckedPreviously) {
                hintLetters[j] = Hint.SEMICORRECT;
                index = templateWordLetters.indexOf(letter);
                templateWordLetters.set(index, "");
            } else if (!letter.equals("")) {
                hintLetters[j] = Hint.INCORRECT;
            }
            j++;
        }
    }
}
