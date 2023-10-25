/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Common.Login;
import java.util.ArrayList;

/**
 *
 * @author QuaVi
 * @param <T>
 */
public abstract class Menu<T> {

    protected String title;
    protected ArrayList<T> mChon;
    Login login = new Login();

    public Menu(String td, String[] mc) {
        title = td;
        mChon = new ArrayList<>();
        for (String s : mc) {
            mChon.add((T) s);
        }
    }

    public void display() {
        System.out.println(title);
        System.out.println("-----------------------------");
        for (int i = 0; i < mChon.size(); i++) {
            System.out.println("|  " + (i + 1) + ". " + mChon.get(i));
        }
        System.out.println("-----------------------------");
    }
    
    public int getSelected(){
        display();
        return login.getIntForMenu("Enter your choice: ", 1, mChon.size());
    }
    
    public abstract void executed(int n);
    
    public void run(){
        while (true) {            
            int n = getSelected();
            executed(n);
        }
    }
}