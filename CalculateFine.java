package library;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class CalculateFine implements IOOperation{

    @Override
    public void oper(Database database,User user) {
        // TODO Auto-generated method stub
        System.out.println("Enter book name: ");
        Scanner s = new Scanner(System.in);
        String bookname = s.nextLine();
        int bookIndex = database.getBook(bookname);
        if(bookIndex==-1){
            System.out.println("Book not available or does not exist in the library.");
            user.menu(database, user);
            return;
        }
        boolean found = false;

        for(Borrowing b: database.getBrws()){
            if(b.getBook().getName().equalsIgnoreCase(bookname.trim())&&
            b.getUser().getName().equalsIgnoreCase(user.getName().trim())){
                
                found = true;

                int daysLate = b.getDaysLeft();
                
                if(daysLate<0){
                    System.out.println("You are late!" +
                    "You have to pay "+Math.abs(b.getDaysLeft()*50)+" as fine");
                }else{
                    System.out.println("You don't have to pay fine\n");
                }
                break;
            }
        }
    if(!found){
        System.out.println("You didn't borrow book");
    }
        user.menu(database, user);
    }
}
