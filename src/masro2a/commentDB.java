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
import java.util.Scanner;

/**
 *
 * @author el mostafa
 */
public class commentDB {
     private Connection connect ; 
    private Statement state ;
    private ResultSet result ; 
    public commentDB()
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
    public void insertDB(String name ,String date , int id )
    {
        try{
            
            state.executeUpdate("INSERT INTO admin (name , date , id )" + "VALUES ('"+name+"' , '"+date+"' ,'"+id+"')");
                
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public void commentDB(String data)
    {
        try{
            
            result=state.executeQuery("select *from comment");
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
    public void deleteDB (int id)
    {
        try{
            
            state.executeUpdate("DELETE FROM comment WHERE id ='"+id+"'");
                
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
   public void updateDB(int id)
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
                            + "\n2- date");
                           
                    int number = input.nextInt() ;
                    System.out.println("Enter value");
                    String value = input.next() ;
                    
                    if (number==1){
                        state.executeUpdate("UPDATE admin SET name='"+value+"' WHERE id ='"+id+"' ");
                    }
                    else if (number==2){
                        state.executeUpdate("UPDATE admin SET date='"+value+"' WHERE id ='"+id+"' ");
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
