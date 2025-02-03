package library;

import java.util.Scanner;

public class ReturnBook implements IOOperation{

    @Override
    public void oper(Database database,User user) {
        // TODO Auto-generated method stub
        System.out.println("Enter book name: ");
        Scanner s = new Scanner(System.in);
        String bookname = s.nextLine();
        boolean bookFound = false;
        int bookIndex = database.getBook(bookname);
        if(bookIndex==-1){
            System.out.println("Book not available or does not exist in the library.");
            user.menu(database, user);
            return;
        }
        if(!database.getBrws().isEmpty()){
            for(Borrowing b: database.getBrws()){
                if(b.getBook().getName().equalsIgnoreCase(bookname)&&
                    b.getUser().getName().equalsIgnoreCase(user.getName())){
                        bookFound = true;
                        Book book = b.getBook();
                        int i = database.getAllBooks().indexOf(book);
                        if(b.getDaysLeft()<0){
                            System.out.println("You are late!\n"
                            +"You have to pay "+Math.abs(b.getDaysLeft()*50)+" as fine\n");
                        }
                        book.setBrwcopies(book.getBrwcopies()+1);
                        database.returnBook(b,book,i);
                        System.out.println("Book returned\nThank you!");
                        break;
                    }
            }
            if(!bookFound){
                System.out.println("You didn't borrow this book!");
            }
        }
        else{
            System.out.println("You didn't borrow this book!");
        }
        user.menu(database,user);
    }
    
}
