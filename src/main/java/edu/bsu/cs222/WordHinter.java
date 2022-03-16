package edu.bsu.cs222;

public class WordHinter {
    String template;
    public WordHinter(String word) {
        template = word;
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
}
