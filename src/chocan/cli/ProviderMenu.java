package chocan.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chocan.service.ChocAnSystem;
//Owen Taylor
public class ProviderMenu {
    private final ChocAnSystem system;
    String providerNumber;

    public ProviderMenu(ChocAnSystem system) 
    {
        this.system = system;
        try {
            MenuStart();
        }
        catch(IOException e) {

        }
    }

    public void MenuStart() throws IOException 
    {
        String command = "";
        while(command != "exit"){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Please enter member number:");
            String membernumber = br.readLine();
            
            if(!system.validateMember(membernumber)) {
                System.out.println("Invalid credentials");         
                continue;
            }

            System.out.println("Add Service with service number:");
            
            String serviceNumber = br.readLine();
        }
    }
}