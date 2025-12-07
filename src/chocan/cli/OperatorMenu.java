package chocan.cli;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.HashMap;

import chocan.model.Member;
import chocan.service.ChocAnSystem;
//Owen Taylor
public class OperatorMenu {
    private final ChocAnSystem system;

    Path appDir = Paths.get(
        System.getProperty("user.home"),
        ".myapp"
    );

    Path saveFile = appDir.resolve("records.dat");


    public OperatorMenu(ChocAnSystem system) 
    {
        this.system = system;
        try {
            Files.createDirectories(appDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void MenuStart() throws IOException 
    {
        String command = "";
        String command2 = "";
        while(command != "exit"){
            System.out.println("Please enter either (A), (D), or (E) (or exit to close)");
            System.out.println("Add (A), Delete (D), Edit (E), (exit):");
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command = br.readLine();
            System.out.println("Member or Provider? (M or D");
            command2 = br.readLine();
            
            
            String number; 
            String name; 
            String city; 
            String address;
            String zipCode;
            String state;

            switch (command) {
                case "A":

                    System.out.println("Please enter member number:");
                    number = br.readLine();
                    System.out.println("Please enter name:");
                    name = br.readLine();
                    System.out.println("Please enter city:");
                    city = br.readLine();
                    System.out.println("Please enter address:");
                    address = br.readLine();
                    System.out.println("Please enter zip code:");
                    zipCode = br.readLine();
                    System.out.println("Please enter state:");
                    state = br.readLine();

                    Member newMember = new Member(number, name, address, city, state, zipCode);
                    system.addMember(newMember);

                    break;
                case "D":
                    
                    System.out.println("Please enter member number:");
                    number = br.readLine();

                    system.deleteMember(number);
                    
                case "E":
                    System.out.println("Please enter member number of member to edit:");
                    number = br.readLine();
                    System.out.println("Please enter new name:");
                    name = br.readLine();
                    System.out.println("Please enter new city:");
                    city = br.readLine();
                    System.out.println("Please enter new address:");
                    address = br.readLine();
                    System.out.println("Please enter new zip code:");
                    zipCode = br.readLine();
                    System.out.println("Please enter new state:");
                    state = br.readLine();
                default:
                    System.out.println("Unknown command: " + command);
                    break;
            }
        }

    }
    
    public static void saveToFile(HashMap<String, Member> map, String filepath)
            throws IOException {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(filepath))) {
            out.writeObject(map);
        }
    }

}