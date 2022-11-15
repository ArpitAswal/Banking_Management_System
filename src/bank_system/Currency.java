/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_system;

/**
 *
 * @author Acer
 */
public class Currency {
 private static  double rup = 0.0;
    
    public Currency(double bal,String code){
         String[] arr = {"INR", "USD", "JPY", "RUB", "KRW"};
        
         int count = 0;
        for (String i : arr) {//it move one by one until it maches the received code with any one of array index value(string)
            if (code.equals(i)) {
                break;  //when it equals it break the for enhanced loop and stop count variable
            }
            count++;
        }
          switch (count) {  //we use switch so we can perfom selected operation in one step

                case 0:
                    rup =bal;
                    break;
                case 1:
                    rup = bal * 0.012922;
                   
                    break;
                case 2:
                    rup = bal * 1.791896;
                  
                    break;
                case 3:
                    rup = bal * 0.751826;
                   
                    break;
                case 4:
                    rup = bal * 16.777960;
              
                    break;
                    
                default:
                    break;
            }
    }
    
    public static double getCurrency(){
        return rup;
    }
}
