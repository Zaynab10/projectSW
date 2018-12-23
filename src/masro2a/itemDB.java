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
public class itemDB {
     private Connection connect ; 
    private Statement state ;
    private ResultSet result ; 
    public itemDB()
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
    public void insertDB(String description ,String photo,String type,int postid )
    {
        try{
            
            state.executeUpdate("INSERT INTO item (describtion , photo , type , postid)" + "VALUES ('"+description+"' , '"+photo+"' ,'"+type+"' , '"+postid+"')");
                
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public void get(String data , String type)
    {
        try{
            
            result=state.executeQuery("select *from item");
            while(result.next())
            {
                String str=result.getString(data);
                if(str.equals(type)){
                    System.out.println("type : "+str);
                    int post = Integer.parseInt(str=result.getString("postid"));
                    String desc = str=result.getString("describtion");
                    System.out.println("description : "+desc);
                    String photo = str=result.getString("photo");
                    System.out.println("Photo path : "+photo);
                    postDB p = new postDB();
                    p.get("sharedate" , post);
                }
            
            
            }
                
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public void deleteDB (int itemid)
    {
        try{
            
            state.executeUpdate("DELETE FROM admin WHERE adminid ='"+itemid+"'");
                
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
   public void updateDB(int itemid)
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
                            + "\n1- description"
                            + "\n2- photo"
                            + "\n3- type"
                            + "\n4- postid");
                    int number = input.nextInt() ;
                    System.out.println("Enter value");
                    String value = input.next() ;
                    
                    if (number==1){
                        state.executeUpdate("UPDATE item SET description='"+value+"' WHERE itemid ='"+itemid+"' ");
                    }
                    else if (number==2){
                        state.executeUpdate("UPDATE item SET photo='"+value+"' WHERE itemid ='"+itemid+"' ");
                    }
                    else if (number==3){
                        state.executeUpdate("UPDATE item SET type='"+value+"' WHERE itemid ='"+itemid+"' ");
                    }
                    else if (number==4){
                        state.executeUpdate("UPDATE item SET postid='"+value+"' WHERE itemid ='"+itemid+"' ");
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
