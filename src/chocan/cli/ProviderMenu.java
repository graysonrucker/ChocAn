package chocan.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chocan.service.ChocAnSystem;
import chocan.cli.MainMenu;
//Owen Taylor
public class ProviderMenu {
    private String providerNumber;

    public ProviderMenu(String providerNumber) 
    {
        this.providerNumber = providerNumber;
        try {
            MenuStart();
        }
        catch(IOException e) {

        }
    }

    public void MenuStart() throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Please enter member number:");
        String membernumber = br.readLine();
        
        if(!MainMenu.chocSystem.validateMember(membernumber)) {
            System.out.println("Invalid credentials");         
            return;   
        }

        System.out.println("Add Service with service number:");
        
        String serviceNumber = br.readLine();


    }
}