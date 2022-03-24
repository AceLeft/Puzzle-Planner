package edu.bsu.cs222.model;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class WordGuesser {
    private String template;
    RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/resources/WordGuesserWords.txt", "r");

    public WordGuesser(String word) throws IOException {
        template = word;
    }

    public WordGuesser() throws IOException {
        createNewTemplateWord();
    }

    public String makeClueFromGuess(String guess) {
        if (guess.length() != template.length()) {
            return "";
        }
        ArrayList<String> templateLetters = new ArrayList<>();
        ArrayList<String> guessLetters = new ArrayList<>();
        String[] clueLetters = new String[template.length()];
        //initialize templateLetters and guessLetters
        for(int i = 0; i < template.length(); i++){
            templateLetters.add(String.valueOf(template.charAt(i)));
            guessLetters.add(String.valueOf(guess.charAt(i)));
        }

        for(int i = 0; i < template.length(); i++){
            char character = guess.charAt(i);
            if (template.charAt(i) == character) {
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

    public void createNewTemplateWord() throws IOException {
        int randomNumber = (int) (Math.random() * randomAccessFile.length());
        int bytes = (int) (randomAccessFile.length() / 828);
        while (randomNumber % bytes != 0) {
            randomNumber -= 1;
        }
        randomAccessFile.seek(randomNumber);
        template = randomAccessFile.readLine();
    }

    public String getTemplate() {
        return template;
    }

    public boolean isTemplate(String guess) {
        return guess.equals(template);
    }
}
