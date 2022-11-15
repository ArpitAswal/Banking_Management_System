/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KakasHi
 */
public class User_Info {
    private static String Name;
    private static String Address;
    private static double Bal;
    private static String Password;
    private static long Account_No;
    private static long Phone_No;
    private static String ISOcode;
    private static long b=1001;
  static long User_Id=0;
    public User_Info(String n, String ad, String pass, long ph, double ba ) throws ClassNotFoundException, SQLException{
        Name=n;
        Address=ad;
        Bal=ba;
        Password=pass;
       Phone_No=ph;
       
      long ac= find_account(0);
       
     javax.swing.JOptionPane.showMessageDialog(null,"Account_No="+ac);
            
          ISOcode="INR";
         String sql = "INSERT INTO account_details(User_Id,User_Name,Address,Account_No,Password,Phone_No,Balance,Currency) VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1,User_Id);
            pstmt.setString(2, Name);
            pstmt.setString(3, Address);
            pstmt.setLong(4,Account_No);
            pstmt.setString(5,Password);
            pstmt.setLong(6,Phone_No);
            pstmt.setDouble(7,Bal);
            pstmt.setString(8,ISOcode);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
   
 
    private boolean check_Id(long b) throws ClassNotFoundException{
       try{ 
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","");
            PreparedStatement pt=con.prepareStatement("select User_Id from account_details where User_Id='"+b+"'");
            ResultSet rs=pt.executeQuery();
            if(rs.next()){
              return true; 
            }
            
        }
    catch(SQLException e){
        }
        return false;
}
    private  long find_account(long acc) throws ClassNotFoundException{
        //User_Id=b++;
        int length=5;
        String numbers="0123456789";
       // String account=numbers;
        Random r=new Random();
        
        char[] a=new char[length];
      // for(int i=0;i<=First_Demo.n;i++){ 
          for(int j=0;j<length;j++)
            a[j]=numbers.charAt(r.nextInt(numbers.length()));
         
        String str=String.valueOf(a);
         if(str.length()==5)     
         Account_No=Long.parseLong(str);
         else
          find_account(acc);
      // } 
        while(check(Account_No))
            find_account(Account_No);
        
        User_Id=b++;
        while(check_Id(User_Id))
          ++User_Id;
       
             
        return Account_No;
        //return Integer.parseInt(String.valueOf(a));
    }
  private boolean check(long acc) throws ClassNotFoundException{
       try{ 
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","");
            PreparedStatement pt=con.prepareStatement("select Account_No from account_details where Account_No='"+acc+"'");
            ResultSet rs=pt.executeQuery();
            if(rs.next()){
              return true; 
            }
            
        }
    catch(SQLException e){
        }
        return false;
}
    public static long getUser_Id(){
        return User_Id;
    } 
  
    public static String getAddress(){
        return Address;
    }
    public static String getName(){
        return Name;
    }
    public static double getBal(){
            return Bal;
    }
    public static long getAccount_No(){
        return Account_No;
    }
    public static String getPassword(){
        return Password;
    }
    public static Long getPhone_No(){
        return Phone_No;
    }
    public static String getISOcode(){
        return ISOcode;
    }
   
    public static void withdraw(double b,long account){
       try{
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","")) {
                PreparedStatement pt=con.prepareStatement("select * from account_details where Account_No ='"+account+"'");
                ResultSet rs=pt.executeQuery();
                if(rs.next()){
                    double rup=rs.getDouble("Balance");
                    // we extract balance from the given account which is stored on database
                    if(rup>b){
                        //if our saved balance is greater than withdraw balance then we can withdraw the money
                        double new_Bal=rup-b;
                        String sql = "UPDATE account_details SET BALANCE = '"+new_Bal+"' WHERE Account_No ='"+account+"'" ;
                        pt.executeUpdate(sql); //update the new balance on database on given account
                        Bal=new_Bal;  //we have to store new_bal also in static balance variable so in future when we call the withdraw balance to getBal() method it show us how money we have save
                    }
                    else{
                        javax.swing.JOptionPane.showMessageDialog(null,"Insufficient Balance");
                        //new_Bal=rup;
                        
                    }
                    // Bal=new_Bal;
                }
                else{
                    javax.swing.JOptionPane.showMessageDialog(null,"Enter correct Details");
                }}
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      }
    
    public static void deposit(double b, long account){
      
        try{
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","")) {
                PreparedStatement pt=con.prepareStatement("select * from account_details where Account_No ='"+account+"'");
                ResultSet rs=pt.executeQuery();
                
                if(rs.next()){
                    double rup=rs.getDouble("Balance");
                    double new_Bal=rup+b;
                    String sql = "UPDATE account_details SET BALANCE = '"+new_Bal+"' WHERE Account_No ='"+account+"'" ;
                    pt.executeUpdate(sql);
                    
                    Bal=new_Bal;
                }
                else{
                    javax.swing.JOptionPane.showMessageDialog(null,"Enter correct Password");
                }
            }
            
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static Connection connect(){ //To change body of generated methods, choose Tools | Templates.
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    
    }
}
