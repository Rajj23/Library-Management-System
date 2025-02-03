package library;

import java.util.Scanner;
public class Main {
    static Scanner sc;
    static Database database;

    public static void main(String[] args) {

        database = new Database();

       int num;
    //    do{
        System.out.println("Welcome to Library Management System!");
        System.out.println("0. Exit\n" + "1.Login\n2. New User");

        sc = new Scanner(System.in);
        num = sc.nextInt();

       switch (num) {
           case 1: 
           login();
           break;
           case 2: newuser();
           break;
           default: System.out.println("Error!");
           break;
       }
    //    }while(num!=0);
    }

    private static void login(){
        System.out.println("Enter phone number: ");
        String phonenumber = sc.next();
        System.out.println("Enter email: ");
        String email = sc.next();
        int n = database.login(phonenumber, email);
        if(n!=-1){
            User user = database.getUser(n);
            System.out.println("Welcome, " + user.getName() + "! You are now logged in.");
            user.menu(database,user);
        }else{
            System.out.println("User doesn't exist!");
        }
    }

    private static void newuser(){
        System.out.println("Enter Name: ");
        String name = sc.next();
        if(database.userExists(name)){
            System.out.println("User already exists! Please choose a different username.");
            newuser();
        }
        System.out.println("Enter phone number: ");
        String phonenumber = sc.next();
        System.out.println("Enter email: ");
        String email = sc.next();
        System.out.println("1. Admin\n2. Student");
        int n2 = sc.nextInt();
        User user;
        if(n2==1){
            user = new Admin(name,email,phonenumber);
        }
        else{
           user = new NormalUser(name,email,phonenumber);
        }
        database.AddUser(user);
        System.out.println("Welcome, " + user.getName() + "! Your account has been created.");
        user.menu(database,user);
    }
}