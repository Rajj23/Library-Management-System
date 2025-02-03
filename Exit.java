package library;

import java.util.Scanner;

public class Exit implements IOOperation {
    Scanner s;
    Database database;
    User user;

    @Override
    public void oper(Database database, User user) {
        this.database=database;
        this.user=user;
        System.out.println("\n Are you sure that you want to quit?\n" + "1. Yes\n2. Main Menu");
         s = new Scanner(System.in);
        int i = s.nextInt();
        if (i == 1) {
            System.out.println("0. Exit\n" + "1.Login\n2. New User");
            int num = s.nextInt();
            switch (num) {
                case 1:
                    login();
                    break;
                case 2:
                    newuser();
                    break;
                default:
                    System.out.println("Error!");
            }
        } else {
            user.menu(database, user);
        }

    }
    
    private void login(){
        System.out.println("Enter phone number: ");
        String phonenumber = s.next();
        System.out.println("Enter email: ");
        String email = s.next();
        int n = database.login(phonenumber, email);
        if(n!=-1){
            User user = database.getUser(n);
            user.menu(database,user);
        }else{
            System.out.println("User doesn't exist!");
            user.menu(database,user);
        }
    }
    private void newuser(){
        System.out.println("Enter Name: ");
        String name = s.next();
        System.out.println("Enter phone number: ");
        String phonenumber = s.next();
        System.out.println("Enter email: ");
        String email = s.next();
        System.out.println("1. Admin\n2. Normal User");
        int n2 = s.nextInt();
        User user;
        if(n2==1){
            user = new Admin(name,email,phonenumber);
        }
        else{
           user = new NormalUser(name,email,phonenumber);
        }
        database.AddUser(user);
        user.menu(database,user);
    }

}
