package edu.bsu.cs222.model;

import java.util.ArrayList;

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

    public boolean isTemplate(String guess) {
        return guess.equals(templateWord);
    }

    /*
    Returns a key representing the relation of letters in the guess
    with the letters in the template. A "-" means the guess letter
    is not in the template, a "*" means the guess letter is in the
    template but not in that spot, and a letter means the guess
    letter was in the template and in the correct spot.
     */
    public String makeClueFromGuess(String guess) {
        guess = guess.toLowerCase();
        if (guess.length() != templateWord.length()) {
            return "";
        }
        templateWordLetters = makeArrayListOfLetters(templateWord);
        guessLetters = makeArrayListOfLetters(guess);
        String[] clueLetters = new String[templateWord.length()];
        findCorrectLetters(clueLetters);
        findIncorrectAndSemiCorrectLetters(clueLetters);
        return formatClue(clueLetters);
    }

    private ArrayList<String> makeArrayListOfLetters(String word) {
        ArrayList<String> wordLetters = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            String wordLetter = String.valueOf(word.charAt(i));
            wordLetters.add(wordLetter);
        }
        return wordLetters;
    }

    private void findCorrectLetters(String[] clueLetters) {
        String curGuessLetter, curTemplateWordLetter;
        for (int i = 0; i < templateWordLetters.size(); i++) {
            curGuessLetter = guessLetters.get(i);
            curTemplateWordLetter = templateWordLetters.get(i);
            if (curTemplateWordLetter.equals(curGuessLetter)) {
                clueLetters[i] = curGuessLetter;
                //"remove" the letter so it cannot be checked again
                templateWordLetters.set(i, "");
                guessLetters.set(i, "");
            }
        }
    }

    private void findIncorrectAndSemiCorrectLetters(String[] clueLetters) {
        int j = 0;
        int index;
        boolean letterPreviouslyChecked;
        for (String letter : guessLetters) {
            letterPreviouslyChecked = letter.equals("");
            if (templateWordLetters.contains(letter) && !letterPreviouslyChecked) {
                clueLetters[j] = "*";
                index = templateWordLetters.indexOf(letter);
                templateWordLetters.set(index, "");
            } else if (!letter.equals("")) {
                clueLetters[j] = "-";
            }
            j++;
        }
    }

    private String formatClue(String[] clueLetters) {
        StringBuilder clue = new StringBuilder();
        for (String key : clueLetters) {
            clue.append(key);
        }
        return clue.toString();
    }


}
