package library;

import java.util.Scanner;

public class Search implements IOOperation{
    @Override
    public void oper(Database database,User user){
        System.out.println("\nEnter book name: ");
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();
        int i = database.getBook(name);
        if(i>-1){
           System.out.println("\n"+database.getBook(i).toString()+"\n");
           }
           else{
            System.out.println("Book not available or does not exist in the library.");
           }
           user.menu(database, user);
    } 
}
