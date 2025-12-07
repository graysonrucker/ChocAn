package chocan;

import java.io.IOException;

import chocan.cli.MainMenu;
import chocan.service.ChocAnSystem;
//Owen Taylor
public class Main{
    public static void main(String[] args){
        ChocAnSystem system = new ChocAnSystem();
        try {
        system.loadData("data");   // <--- RIGHT HERE
        } catch (IOException e) {
            System.out.println("Warning: Could not load data.");
        }

        MainMenu mainMenu = new MainMenu(system);
         try {
            mainMenu.start();
        }
        catch (IOException exception) {
            System.out.println("IO Exception:" + exception);
        }
    }
}