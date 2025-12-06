package chocan;

import java.io.IOException;

import chocan.cli.MainMenu;
import chocan.service.ChocAnSystem;
//Owen Taylor
public class Main{
    public static void main(String[] args){
        ChocAnSystem chocSystem = new ChocAnSystem();
        MainMenu mainMenu = new MainMenu(chocSystem);
         try {
            mainMenu.start();
        }
        catch (IOException exception) {
            System.out.println("IO Exception:" + exception);
        }
    }
}