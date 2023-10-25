/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Common.Login;
import View.Menu;
import java.util.Locale;

/**
 *
 * @author QuaVi
 */
public class MenuChoice extends Menu<String>{

    Login login = new Login();
    Locale vietnamese = new Locale("vi");
    Locale english = Locale.US;
    
    static String[] mc = {"Vietnamese", "English","Exit"};
    public MenuChoice(){
        super("TienPhong Bank", mc);
    }
    @Override
    public void executed(int n) {
        switch (n) {
            case 1:
                login.login(vietnamese);
                break;
            case 2:
                login.login(english);
                break;
            case 3:
                System.out.println("End program");
                System.exit(0);
            default:
                throw new AssertionError();
        }
    }

    
}