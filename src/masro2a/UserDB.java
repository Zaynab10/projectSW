/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masro2a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Clock;
import java.util.Scanner;

/**
 *
 * @author el mostafa
 */
public class UserDB{
    private Connection connect ; 
    private Statement state ;
    private ResultSet result ; 
    public UserDB()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance() ;
            connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/msr2a" , "root" , "") ;
            state=connect.createStatement() ;
        }
        catch(Exception ex)
        {
            System.out.println("Error : " + ex);
        }
    }
    
    public void insertDB(String name ,int pass , String email , int phone )
    {
        try{
            
            state.executeUpdate("INSERT INTO user (name , pass , email , phone)" + "VALUES ('"+name+"' , '"+pass+"' ,'"+email+"' , '"+phone+"')");
                
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public void getUserDB(String data)
    {
        try{
            
            result=state.executeQuery("select *from user");
            while(result.next())
            {
                String str=result.getString(data);
                System.out.println(str);
            }
                
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public String getUser(String data , String email)
    {
        try{
            result=state.executeQuery("select *from user where email = \""+email+'"');
            while(result.next())
            {
                String str=result.getString(data);
                return str;
            }
                
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return "ydg";
    }
    
    
    public void deleteDB (int userid)
    {
        try{
            
            state.executeUpdate("DELETE FROM user WHERE adminid ='"+userid+"'");
                
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
   public void updateDB(int userid)
    {
        Scanner input = new Scanner(System.in) ;
        
        try{
            
            while (true)
            {
                System.out.println("do you want to update thing else (yes or no)");
                String choice = new String() ;
                choice=input.next();
                if (choice.equals("yes"))
                {
                    System.out.println("whate you to update"
                            + "\n1- name"
                            + "\n2- pass"
                            + "\n3- email"
                            + "\n4- phone");
                    int number = input.nextInt() ;
                    System.out.println("Enter value");
                    String value = input.next() ;
                    
                    if (number==1){
                        state.executeUpdate("UPDATE user SET name='"+value+"' WHERE adminid ='"+userid+"' ");
                    }
                    else if (number==2){
                        state.executeUpdate("UPDATE user SET pass='"+value+"' WHERE adminid ='"+userid+"' ");
                    }
                    else if (number==3){
                        state.executeUpdate("UPDATE user SET email='"+value+"' WHERE adminid ='"+userid+"' ");
                    }
                    else if (number==4){
                        state.executeUpdate("UPDATE user SET phone='"+value+"' WHERE adminid ='"+userid+"' ");
                    }
                }
                else if (choice.equals("no"))
                    break ;
                else 
                    continue;
            }
            
                
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}