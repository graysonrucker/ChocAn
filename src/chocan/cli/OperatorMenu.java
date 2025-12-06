package chocan.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chocan.service.ChocAnSystem;
//Owen Taylor
public class OperatorMenu {
    private final ChocAnSystem system;

    public OperatorMenu(ChocAnSystem system) 
    {
        this.system = system;
    }

    public void MenuStart() throws IOException 
    {
        String command = "";
        while(command != "exit"){
            System.out.println("Please enter either (A), (D), or (E) (or exit to close)");
            System.out.println("Add (A), Delete (D), Edit (E), (exit):");
            System.out.println("Member or Provider? (M or D");
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command = br.readLine();
            command = br.readLine();
            

            switch (command) {
                case "A":
                    
                case "D":
                    
                case "U":
                    
                default:
                    System.out.println("Unknown command: " + command);
                    break;
            }
        }

    }

}