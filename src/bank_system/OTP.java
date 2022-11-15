/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_system;

/**
 *
 * @author KakasHi
 */
import java.util.*;
public class OTP {
    private static int otp;
     public OTP(){
        String numbers="1234567890";
        Random r=new Random();
        char[] c=new char[4];
        for(int i=0;i<4;i++){
            c[i]=numbers.charAt(r.nextInt(numbers.length()));
            //here bound is string length i.e,9 and by r.nextInt it randomly select no from 0 to 8 then we use this no as a index of string characters
        }
       
          otp=Integer.parseInt(String.valueOf(c));// convert string into integer number
         javax.swing.JOptionPane.showMessageDialog(null,"OTP -> "+otp);

    }
    
    
   public static int getOTP(){ 
return otp;
   }
   

}
/* Random class
it is a class of java.util.package, it generates a stream of pseudorandom numbers. we can generate a no of any data type,
such as int,float,double,boolean,long.
for this following step we have to follow-
1.first we have to import java.lang.Random or java.util.*;
2.create an object of the Random class
3.Invoke any data type method
ex here we use int -nextInt()
all the method return the next pseudorandom,homogeneously distributed value from this random number generator sequence.
*The nextDouble() and nextFloat() method generates random value between 0.0 to 1.0
the nextInt(int bound)here bound is max length of numbers which it accepts as a parameter and it must be positive 
it generates a random number in the range 0 to bound-1.
*/