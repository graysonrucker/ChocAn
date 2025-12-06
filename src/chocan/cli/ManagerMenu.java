package chocan.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chocan.service.ChocAnSystem;
//Owen Taylor
public class ManagerMenu {
    private final ChocAnSystem system;

    public ManagerMenu(ChocAnSystem system) 
    {
        this.system = system;
    }

    public void MenuStart() throws IOException 
    {
        System.out.println("Please select one");
        System.out.println("Summary report(S), provider report(P), member report(M):");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String command = br.readLine();
        

        switch (command) {
            case "P":
            case "S":
            case "M":
                
            default:
                System.out.println("Unknown command: " + command);
                break;
        }

    }

}