package View;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    private List<String> choices;
    private String message;

    public Menu(List<String> choices) {
    	this.message = "Please choose ";
        this.choices = choices;
    }

    public Menu(List<String> choices, String message) {
        this.choices = choices;
        this.message = message;
    }

    public int display() throws IOException {
        System.out.println(message);
        for(int i=0;i<choices.size();i++){
            System.out.println(i+" : "+choices.get(i));
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Reading data using readLine
        return Integer.parseInt(reader.readLine());
    }
}