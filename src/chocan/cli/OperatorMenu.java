package chocan.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chocan.model.*;
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
        while(!command.equals("exit")){
            System.out.println("Member (M) or Provider (P)? (enter <exit> to logout");
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command = br.readLine();
            if(command.equals("M")){
                while(!command.equals("exit")){
                    String memberNumber = "";
                    String name = "";
                    String city = "";
                    String address = "";
                    String zipCode = "";
                    String state = "";
                    System.out.println("Add (A), delete (D), or update (U) member? (or <exit> to cancel");
                    command = br.readLine();
                    switch (command) {
                    case "A":
                        System.out.println("Enter member number (9 digits exactly): ");
                        memberNumber = br.readLine();
                        System.out.println("Enter member name (max 25 characters): ");
                        name = br.readLine();
                        System.out.println("Enter member city (max 14 characters): ");
                        city = br.readLine();
                        System.out.println("Enter member address (max 25 characters): ");
                        address = br.readLine();
                        System.out.println("Enter member zipcode (5 digits exactly): ");
                        zipCode = br.readLine();
                        System.out.println("Enter member state (2 letters exactly): ");
                        state = br.readLine();
                        try {
                            Member newMember = new Member(memberNumber, name, city, address, zipCode, state);
                            system.addMember(newMember);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    case "D":
                        System.out.println("Enter member number (9 digits exactly): ");
                        memberNumber = br.readLine();
                        try {
                            system.deleteMember(memberNumber);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    case "U":
                        System.out.println("Enter new member number (9 digits exactly): ");
                        memberNumber = br.readLine();
                        System.out.println("Enter new member name (max 25 characters): ");
                        name = br.readLine();
                        System.out.println("Enter new member city (max 14 characters): ");
                        city = br.readLine();
                        System.out.println("Enter new member address (max 25 characters): ");
                        address = br.readLine();
                        System.out.println("Enter new member zipcode (5 digits exactly): ");
                        zipCode = br.readLine();
                        System.out.println("Enter new member state (2 letters exactly): ");
                        state = br.readLine();
                        try {
                            Member newMember = new Member(memberNumber, name, city, address, zipCode, state);
                            system.updateMember(newMember);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    case ("exit"):
                        break;
                    default:
                        System.out.println("Unrecognized command: " + command);
                        break;
                    }
                }
            }
            else if(command.equals("P")){
                while(!command.equals("exit")){
                    String providerNumber = "";
                    String name = "";
                    String city = "";
                    String address = "";
                    String zipCode = "";
                    String state = "";
                    System.out.println("Add (A), delete (D), or update (U) provider? (or <exit> to cancel");
                    command = br.readLine();
                    switch (command) {
                    case "A":
                        System.out.println("Enter new provider number (9 digits exactly): ");
                        providerNumber = br.readLine();
                        System.out.println("Enter new provider name (max 25 characters): ");
                        name = br.readLine();
                        System.out.println("Enter new provider city (max 14 characters): ");
                        city = br.readLine();
                        System.out.println("Enter new provider address (max 25 characters): ");
                        address = br.readLine();
                        System.out.println("Enter new provider zipcode (5 digits exactly): ");
                        zipCode = br.readLine();
                        System.out.println("Enter new provider state (2 letters exactly): ");
                        state = br.readLine();
                        try {
                            Provider newProvider = new Provider(providerNumber, name, city, address, zipCode, state);
                            system.addProvider(newProvider);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    case "D":
                        System.out.println("Enter provider number (9 digits exactly): ");
                        providerNumber = br.readLine();
                        try {
                            system.deleteProvider(providerNumber);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    case "U":
                        System.out.println("Enter provider number (9 digits exactly): ");
                        providerNumber = br.readLine();
                        System.out.println("Enter new provider name (max 25 characters): ");
                        name = br.readLine();
                        System.out.println("Enter new provider city (max 14 characters): ");
                        city = br.readLine();
                        System.out.println("Enter new provider address (max 25 characters): ");
                        address = br.readLine();
                        System.out.println("Enter new provider zipcode (5 digits exactly): ");
                        zipCode = br.readLine();
                        System.out.println("Enter new provider state (2 letters exactly): ");
                        state = br.readLine();
                        try {
                            Provider newProvider = new Provider(providerNumber, name, city, address, zipCode, state);
                            system.updateProvider(newProvider);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    case ("exit"):
                        break;
                    default:
                        System.out.println("Unrecognized command: " + command);
                        break;
                    }
                }
            }
            else if (command.equals("exit")){
                return;
            }
            else{
                System.out.println("Unrecognized command: " + command);
            }
        }

    }

}