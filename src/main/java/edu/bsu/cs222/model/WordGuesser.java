package edu.bsu.cs222.model;

import java.io.IOException;
import java.io.RandomAccessFile;

public class WordGuesser {
    private String template;
    RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/resources/WordGuesserWords.txt", "r");
    public WordGuesser(String word) throws IOException {
        template = word;
    }
    public WordGuesser() throws IOException{
        createNewTemplateWord();
    }
    public String doLettersMatch(String guess) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < guess.length(); i++ ){
            char character = guess.charAt(i);
            if(template.indexOf(character) == -1){
                result.append("-");
            }
            else if(template.charAt(i) == character){
                result.append(character);
            }
            else{
                result.append("*");
            }

        }

        return result.toString();
    }

    public void createNewTemplateWord() throws IOException {
        int randomNumber = (int)(Math.random()*828);
        randomAccessFile.seek(randomNumber* 8L);
        template = randomAccessFile.readLine();
    }

    public String getTemplate() {
        return template;
    }
}
