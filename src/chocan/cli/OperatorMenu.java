package chocan.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chocan.model.Member;
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
            System.out.println("Member (M) or Provider (P)? (enter <exit> to logout");
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command = br.readLine();
            if(command.equals("M")){
                while(command.equals("exit")){
                    System.out.println("Add (A), delete (D), or update (U) member?");
                    command = br.readLine();
                    switch (command) {
                    case "A":
                        String memberNumber = "";
                        String name = "";
                        String city = "";
                        String address = "";
                        String zipCode = "";
                        String state = "";
                        System.out.println("Enter member number (9 digits exactly): ");
                        name = br.readLine();
                        System.out.println("Enter member name (max 25 characters): ");
                        name = br.readLine();
                        System.out.println("Enter member city (max 14 characters): ");
                        name = br.readLine();
                        System.out.println("Enter member address (max 25 characters): ");
                        name = br.readLine();
                        System.out.println("Enter member zipcode (5 digits exactly): ");
                        name = br.readLine();
                        System.out.println("Enter member state (2 letters exactly): ");
                        name = br.readLine();
                        try {
                            Member newMember = new Member(memberNumber, name, city, address, zipCode, state);
                            system.addMember(newMember);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    case "D":
                        try {
                            
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    case "U":
                        
                    default:
                        System.out.println("Unknown command: " + command);
                        break;
                    }
                }
            }
            else if(command.equals("P")){
                switch (command) {
                    case "A":
                        
                    case "D":
                        
                    case "U":
                        
                    default:
                        System.out.println("Unknown command: " + command);
                        break;
                }
            }
            else if (command.equals("exit")){
                return;
            }
            else{
                System.out.println("Unrecognized command.");
            }
        }

    }

}