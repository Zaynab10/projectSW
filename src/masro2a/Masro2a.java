/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masro2a;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author el mostafa
 */
public class Masro2a {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("enter 1 to sign up or 2 to log in");
        int c = in.nextInt();
        UserDB u = new UserDB();
        if(c == 1){
            int pass , phone;
            String name , email ;
            System.out.print("enter your name : ");
            name= in.next();
            System.out.print("enter your email : ");
            email= in.next();
            System.out.print("enter your password :");
            pass= in.nextInt();
            System.out.print("enter your phone : ");
            phone= in.nextInt();
            u.insertDB( name , pass ,  email ,  phone );   
        }
        else if(c == 2){
            int pass ;
            String email ;
            System.out.print("enter your email : ");
            email= in.next();            
            System.out.print("enter your password :");
            pass= in.nextInt();
            try {
                c = Integer.parseInt(u.getUser("pass", email));
                if(c!=pass)
                    System.out.println("invalid password.");
            } catch (Exception e) {
                System.out.println("invalid email.");
            }
            if(c==pass){
                System.out.println("loged in successfully.");
                System.out.println("enter 1 to post or 2 to Scearch");
                c = in.nextInt();
                postDB p = new postDB();
                if(c==1){
                    int id = p.id()+1;
                    String sharedate , desc ,ques , ans ,  corectans , photo, type;
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    sharedate =dateFormat.format(date) ;
                    System.out.print("describe your post : ");
                    desc = in.next();
                    System.out.print("enter your post question : ");
                    ques = in.next();
                    System.out.print("enter a fake anser : ");
                    ans = in.next();
                    System.out.print("enter correct answer : ");
                    corectans = in.next();
                    System.out.print("enter your photo path : ");
                    photo = in.next();
                    System.out.print("enter your ietm type : ");
                    type = in.next();
                    p.insertDB(id ,  sharedate  ,  desc ,  ques , ans ,  corectans , photo, type);
                }
                if(c==2){
                    System.out.print("enter item name : ");
                    String name = in.next();
                    itemDB i = new itemDB();
                    i.get("type",name);
                }
            }
        }
        
    }
    
}
