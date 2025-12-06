package chocan.cli;
import chocan.service.ChocAnSystem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Owen Taylor
public class MainMenu {

    public static ChocAnSystem chocSystem = new ChocAnSystem();

    public MainMenu(ChocAnSystem system) 
    {
        chocSystem = system;
        try {
            MenuStart();
        }
        catch (IOException exception) {
            System.out.println("IO Exception:" + exception);
        }
    }

    public void MenuStart() throws IOException 
    {
        System.out.println("Clarify user type or print weekly accounting procedure");
        System.out.println("Operator (O), Provider (P), Manager (M):");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String command = br.readLine();
        
        String username;
        String password;

        switch (command) {
            case "P":
                System.out.println("Enter provider number");
                String pnumber = br.readLine();
                if(chocSystem.verifyProvider(pnumber)) {
                    ProviderMenu PMenu = new ProviderMenu(pnumber);
                }
                break;
            case "O":
                System.out.println("Enter username");
                username = br.readLine();
                System.out.println("Enter password");
                password = br.readLine();
                if(chocSystem.verifyOperator(username, password)) {
                    OperatorMenu OMenu = new OperatorMenu(username, password);
                }
                else {
                    System.out.println("Invalid credentials");
                }
                break;
            case "M":
                System.out.println("Enter username");
                username = br.readLine();
                System.out.println("Enter password");
                password = br.readLine();
                if(chocSystem.verifyManager(username, password)) {
                    ManagerMenu MMenu = new ManagerMenu(username, password);
                }
                else {
                    System.out.println("Invalid credentials");
                }
                break;
                
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
        MenuStart();
    }
}