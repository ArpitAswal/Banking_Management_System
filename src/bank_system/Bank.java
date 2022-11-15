/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_system;

import java.sql.*;

/**
 *
 * @author KakasHi
 */
public class Bank {

    static public User_Info ob[] = new User_Info[1000];
   //we create a array to store user info in current window
    static public int n = 0;  //no of account in current array of user_info
   static public int index = 0;  //it need when we found the searching account so we can hold the index of that account

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
        javax.swing.JOptionPane.showMessageDialog(null, "Welcome to the 2A Star Bank \n"
        +"we provide you all the necessary functions of BankAccount\n"
        +"If you are a new user first you have to create an account otherwise you can simply login\n"
        +"we hope you can enjoy our bank services\n");
        javax.swing.JOptionPane.showMessageDialog(null, "Have a GoodDay");
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LogIn().setVisible(true);  //it open LogIn window
            }
        });
        //Now we connect the JDBC in our program
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb", "root", "")) {
                PreparedStatement pt = con.prepareStatement("select * from account_details order by User_Name,User_Id");
                ResultSet rs = pt.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getString(5) + " " + rs.getLong(6) + " " + rs.getLong(7)+ " " +rs.getString(8));
                }
                // here we print the information of founding account means every detail of a user which we save in our database
            }
        } catch (SQLException e) {
            System.out.println("Something is wrong in fetching data from database");
        }

    }

}
