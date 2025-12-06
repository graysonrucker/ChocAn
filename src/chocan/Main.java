package chocan;

import chocan.cli.MainMenu;
import chocan.service.ChocAnSystem;
//Owen Taylor
public class Main{
    public static void main(String[] args){
        chocan.service.ChocAnSystem chocSystem = new ChocAnSystem();
        MainMenu main = new MainMenu(chocSystem);
    }
}