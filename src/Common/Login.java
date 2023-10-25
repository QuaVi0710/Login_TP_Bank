/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author QuaVi
 */
public class Login {

    Scanner in = new Scanner(System.in);

    private static final String ACCOUNT_NUMBER_VALID = "^\\d{10}$";
    private static final char[] chars = {'1', 'A', 'a', 'B', 'b', 'C',
        'c', '2', 'D', 'd', 'E', 'e', 'F', 'f', '3', 'G', 'g', 'H', 'h',
        'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', '4', 'M', 'm', 'N', 'n',
        'O', 'o', '5', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't',
        '6', '7', 'U', 'u', 'V', 'v', 'U', 'u', 'W', 'w', '8', 'X', 'x',
        'Y', 'y', 'Z', 'z', '9'};
 public String getValue(String msg){
        System.out.print(msg);
        return in.nextLine();
    }
    public int getIntForMenu(String msg, int min, int max){
        int a = -1;
        while(true){
            System.out.print(msg);
            try {
                a = Integer.parseInt(in.nextLine());
                if(a >= min && a <= max){
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a number between " + min + " and " + max);
            }
        }
        return a;
    }

    public String checkInputString(Locale language) {
        while (true) {
            String result = in.nextLine();
            if (result.length() == 0) {
                getLocalizedString(language, "errCheckInputIntLimit");
                System.out.println();
            } else {
                return result;
            }
        }
    }

    public int checkInputAccount(Locale language) {
        while (true) {
            String result = in.nextLine();
            if (result.matches(ACCOUNT_NUMBER_VALID)) {
                return Integer.parseInt(result);
            }
            getLocalizedString(language, "errCheckInputAccount");
            System.out.println();
        }
    }

    public String checkInputPassword(Locale language) {
        String result;
        while (true) {
            result = checkInputString(language);
            if (isValidPassword(result, language)) {
                return result;
            }
        }
    }

    public boolean isValidPassword(String password, Locale language) {
        int lengthPassword = password.length();
        if (lengthPassword < 8 || lengthPassword > 31) {
            getLocalizedString(language, "errCheckLengthPassword");
            System.out.println();
            return false;
        } else {
            int countDigit = 0;
            int countLetter = 0;
            for (int i = 0; i < lengthPassword - 1; i++) {
                if (Character.isDigit(password.charAt(i))) {
                    countDigit++;
                } else if (Character.isLetter(password.charAt(i))) {
                    countLetter++;
                }
            }
            if (countDigit < 1 || countLetter < 1) {
                getLocalizedString(language, "errCheckAlphanumericPassword");
                System.out.println();
                return false;
            }
        }
        return true;
    }

    public boolean CheckValidcaptcha(String captchaGenerated, Locale language) {
        System.out.println(captchaGenerated);
        getLocalizedString(language, "enterCaptcha");
        String captchaInput = checkInputString(language);
        for (int i = 0; i < captchaInput.length(); i++) {
            if (!captchaGenerated.contains(Character.toString(captchaInput.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public String generateCaptcha() {
        String randomStrValue = "";
        final int LENGTH = 6;
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = 0; i < LENGTH; i++) {
            index = (int) (Math.random() * (chars.length - 1));
            sb.append(chars[index]);
        }
        return sb.toString();
    }

    public void getLocalizedString(Locale curLocate, String key) {
        ResourceBundle words = ResourceBundle.getBundle("messages", curLocate);
        String value = words.getString(key);
        System.out.printf(value);
    }
    public void login(Locale language) {
        getLocalizedString(language, "enterAccountNumber");
        int accountNumber = checkInputAccount(language);
        getLocalizedString(language, "enterPassword");
        String passString = checkInputPassword(language);
        String captchaGenerated = generateCaptcha();
        while (true) {
            if (CheckValidcaptcha(captchaGenerated, language)) {
                getLocalizedString(language, "loginSuccess");
                System.out.println();
                return;
            } else {
                getLocalizedString(language, "errCaptchaIncorrect");
                System.out.println();
            }
        }
    }
}
