/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_system;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Acer
 */
public class Password_Strength {
    private static boolean strong=false;
    public Password_Strength(String input){
        
      int n = input.length();
        boolean Lower = false, Upper = false,
                Digit = false, specialChar = false;
        Set<Character> set = new HashSet<Character>(
                Arrays.asList('!', '@', '#', '$', '%', '^', '&',
                        '*', '(', ')', '-', '+'));
        for (char i : input.toCharArray()) {
            if (Character.isLowerCase(i)) {
                Lower = true;
            }
         else if (Character.isUpperCase(i)) {
                Upper = true;
            }
            else if (Character.isDigit(i)) {
                Digit = true;
            }
            else if (set.contains(i)) {
                specialChar = true;
            }
        }

        // Strength of password
        if (Digit && Lower && Upper && specialChar && (n >= 7)) // javax.swing.JOptionPane.showMessageDialog(null,"your password is strong");
            strong=true;
        else if (((Lower || Upper) && specialChar) && (n== 7)) {
            javax.swing.JOptionPane.showMessageDialog(null, "your password is not strong enough");
            javax.swing.JOptionPane.showMessageDialog(null, "For Strong Password following conditions should be follow \n"
                    + "It contains at least one lowercase English character.\n"
                    + "It contains at least one uppercase English character.\n"
                    + "It contains at least one special character. The special characters are: !@#$%^&*()-+ \n"
                    + "Its length is at least 7.\n"
                    + "It contains at least one digit\n");
            
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "your password is weak");
            javax.swing.JOptionPane.showMessageDialog(null, "For Strong Password following conditions should be follow \n"
                    + "It contains at least one lowercase English character.\n"
                    + "It contains at least one uppercase English character.\n"
                    + "It contains at least one special character. The special characters are: !@#$%^&*()-+ \n"
                    + "Its length is at least 7.\n"
                    + "It contains at least one digit\n");
            
        }
}
    public static boolean getStrength(){ 
return strong;
   }
}