package chocan.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chocan.model.ProviderDirectory;
import chocan.service.ChocAnSystem;
//Owen Taylor
public class ProviderMenu {
    private final ChocAnSystem system;
    String providerNumber;

    public ProviderMenu(ChocAnSystem system, String providerNumber)
    {
        this.system = system;
        this.providerNumber = providerNumber;
        try {
            MenuStart();
        }
        catch(IOException e) {
    
        }
    }

    public void MenuStart() throws IOException 
    {
        DateTimeFormatter currDateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        boolean validDate = false;
        String command = "";
        String exit = "exit";
        outerLoop:
        while(!command.equals(exit)){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Please enter member number (or type <exit> to logout)");
            command = br.readLine();
            
            if(!system.validateMember(command)) {
                System.out.println("Member number is Invalid, please try again.");         
                continue;
            }

            String memberNumber = new String(command);
            System.out.println("Valid member. Proceed with billing? [Y/N]");
            command = br.readLine();

            caseBlock:
            {
            switch (command) {
                case ("Y"):
                    String dateOfService = "";
                    String serviceCode = "";
                    String comment = "";
                    while(!validDate){
                        System.out.println("Please enter the date service was provided (MM-DD-YYYY), or type <cancel> to abort: ");
                        command = br.readLine();
                        validDate = isValidDate(command, formatter);
                        if(!validDate){
                            if(command.equals("cancel")){
                                break caseBlock;
                            }
                            System.out.println("Invalid date format, please try again.");
                            continue;
                        }
                        else{
                            dateOfService = new String(command);
                        }
                    }
                    
                    boolean validCode = false;
                    while(!validCode){
                        System.out.println("Please enter service code (6 digit number) or type <cancel> to abort: ");
                        serviceCode = br.readLine();
                        if(serviceCode.equals("cancel")){
                            break caseBlock;
                        }
                        if(serviceCode == null || serviceCode.length() != 6
                             || !serviceCode.chars().allMatch(Character::isDigit)){
                            System.out.println("Invalid service code format, please try again.");
                            continue;
                        }
                        if(!ProviderDirectory.hasService(serviceCode)){
                            System.out.println("Service code does not have an associated service, please try again");
                            continue;
                        }
                        String serviceName = ProviderDirectory.getNameByCode(serviceCode);
                        System.out.println("Service code corresponds to: " + serviceName);
                        System.out.println("Correct service name? [Y/N/cancel]");
                        String input = br.readLine();
                        switch(input){
                            case ("Y"):
                                validCode = true;
                                System.out.println("Please enter any service comments (100 character limit)");
                                comment = br.readLine();
                                break;
                            case ("N"):
                                break;
                            case ("cancel"):
                                break caseBlock;
                            default:
                                System.out.println("Unrecognized command.");
                                break;
                        }
                        System.out.println("Creating service record.");

                        LocalDateTime nowDateTime = LocalDateTime.now();
                        String currDateTime = nowDateTime.format(currDateTimeFormatter);
                        system.addServiceRecord(currDateTime, dateOfService, providerNumber, memberNumber, serviceCode, comment);
                    }
                    break;
                case ("N"):
                    System.out.println("Returning to member validation process.");
                    break;
                default:
                    if(command.equals("exit")){
                        break outerLoop;
                    }
                    System.out.println("Unrecognized command, returning to member validation process.");
                    break;
            }
            }
        }
    }

    public String getProviderNumber(){
        return providerNumber;
    }

    private boolean isValidDate(String input, DateTimeFormatter formatter){
        try {
            LocalDateTime.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}