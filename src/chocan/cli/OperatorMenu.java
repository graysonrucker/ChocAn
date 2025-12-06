package chocan.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chocan.service.ChocAnSystem;
//Owen Taylor
public class OperatorMenu {
    private String username;
    private String password;

    public OperatorMenu(String username, String password) 
    {
        this.username = username;
        this.password = password;
    }

    public void MenuStart() throws IOException 
    {
        System.out.println("Please select one");
        System.out.println("Add member(A), Edit member(E), Delete member(D):");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String command = br.readLine();
        

        switch (command) {
            case "A":
            case "E":
            case "D":
                
            default:
                System.out.println("Unknown command: " + command);
                break;
        }

    }

}