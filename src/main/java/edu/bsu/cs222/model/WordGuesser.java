package edu.bsu.cs222.model;

import java.util.ArrayList;

public class WordGuesser {
    private String templateWord;
    private final WordGuesserWordList wordGuesserWordList = new WordGuesserWordList();

    public WordGuesser(String word){
        templateWord = word;
    }

    public WordGuesser(){
        createNewTemplateWord();
    }

    public String makeClueFromGuess(String guess) {
        if (guess.length() != templateWord.length()) {
            return "";
        }
        ArrayList<String> templateLetters = new ArrayList<>();
        ArrayList<String> guessLetters = new ArrayList<>();
        String[] clueLetters = new String[templateWord.length()];
        //initialize templateLetters and guessLetters
        for(int i = 0; i < templateWord.length(); i++){
            templateLetters.add(String.valueOf(templateWord.charAt(i)));
            guessLetters.add(String.valueOf(guess.charAt(i)));
        }

        for(int i = 0; i < templateWord.length(); i++){
            char character = guess.charAt(i);
            if (templateWord.charAt(i) == character) {
                clueLetters[i] = String.valueOf(character);
                templateLetters.set(i,"");
                guessLetters.set(i,"");
            }
        }

        int j = 0;
        for (String string : guessLetters) {
            if(templateLetters.contains(string) && !string.equals("")){
                clueLetters[j] = "*";
                int index = templateLetters.indexOf(string);
                templateLetters.set(index,"");
            }
            else if (!string.equals("")){
                clueLetters[j] = "-";
            }
            j++;

        }
        StringBuilder clue = new StringBuilder();
        for( String key : clueLetters){
            clue.append(key);
        }
        return clue.toString();
    }

    public void createNewTemplateWord(){
        int randomNumber = (int) (Math.random() * wordGuesserWordList.getWordListLength());
        templateWord = wordGuesserWordList.getWordAt(randomNumber);
    }

    public String getTemplateWord(){
        return templateWord;
    }

    public boolean isTemplate(String guess){
        return guess.equals(templateWord);
    }
}
