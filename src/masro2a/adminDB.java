/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masro2a;
import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.* ;
import java.util.Scanner;
/**
 *
 * @author el mostafa
 */
public class adminDB {
    private Connection connect ; 
    private Statement state ;
    private ResultSet result ; 
    public adminDB()
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
            
            state.executeUpdate("INSERT INTO admin (name , pass , email , phone)" + "VALUES ('"+name+"' , '"+pass+"' ,'"+email+"' , '"+phone+"')");
                
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public void getAdminDB(String data)
    {
        try{
            
            result=state.executeQuery("select *from admin");
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
    public void deleteDB (int adminid)
    {
        try{
            
            state.executeUpdate("DELETE FROM admin WHERE adminid ='"+adminid+"'");
                
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
   public void updateDB(int adminid)
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
                        state.executeUpdate("UPDATE admin SET name='"+value+"' WHERE adminid ='"+adminid+"' ");
                    }
                    else if (number==2){
                        state.executeUpdate("UPDATE admin SET pass='"+value+"' WHERE adminid ='"+adminid+"' ");
                    }
                    else if (number==3){
                        state.executeUpdate("UPDATE admin SET email='"+value+"' WHERE adminid ='"+adminid+"' ");
                    }
                    else if (number==4){
                        state.executeUpdate("UPDATE admin SET phone='"+value+"' WHERE adminid ='"+adminid+"' ");
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
