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
public class postDB {
     private Connection connect ; 
    private Statement state ;
    private ResultSet result ; 
    public postDB()
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
    public void insertDB(int id , String sharedate , String desc , String ques ,String ans , String corectans ,String photo,String type)
    {
        try{
            
            state.executeUpdate("INSERT INTO post (id, sharedate , shared)" + "VALUES ('"+id+"' , '"+sharedate+"' ,'"+1+"')");
            questionDB q = new questionDB();
            itemDB i = new itemDB();
            i.insertDB(desc,photo,type,id );
            q.insertDB(desc , ans ,  corectans, id ); 
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public int id(){
        int id = 0;
        try{
            result=state.executeQuery("select *from post");
            while(result.next())
            {
                String str=result.getString("id");
                id = Integer.parseInt(str);
            }
                
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return id;
    }
    public void get(String data , int id)
    {
        try{
            
            result=state.executeQuery("select *from post");
            while(result.next())
            {
                String str=result.getString(data);
                int id2 =Integer.parseInt(result.getString("id"));
                if(id == id2)
                    System.out.println("Date of sharing : "+str);
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
            
            state.executeUpdate("DELETE FROM admin WHERE adminid ='"+id+"'");
                
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
                            + "\n1- sharedate"
                            + "\n2- shared");
                          
                    int number = input.nextInt() ;
                    System.out.println("Enter value");
                    String value = input.next() ;
                    
                    if (number==1){
                        state.executeUpdate("UPDATE admin SET sharedate='"+value+"' WHERE id ='"+id+"' ");
                    }
                    else if (number==2){
                        state.executeUpdate("UPDATE admin SET shared='"+value+"' WHERE id ='"+id+"' ");
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
