package edu.bsu.cs222.model;

import java.io.IOException;
import java.io.RandomAccessFile;

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
        StringBuilder clue = new StringBuilder();
        if (guess.length() > template.length()) {
            return "";
        }
        for (int i = 0; i < guess.length(); i++) {
            char character = guess.charAt(i);
            if (template.indexOf(character) == -1) {
                clue.append("-");
            } else if (template.charAt(i) == character) {
                clue.append(character);
            } else {
                clue.append("*");
            }

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
