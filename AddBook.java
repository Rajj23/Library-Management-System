package library;

import java.util.Scanner;

public class AddBook implements IOOperation{
    @Override
    public void oper(Database database,User user){

        Scanner s = new Scanner(System.in);
        Book book = new Book();
        System.out.println("\nEnter book name: ");
        String name = s.nextLine();
        if(database.getBook(name)>-1){
            System.out.println("There is a book with this name!\n");
            user.menu(database, user);
            return;
        }
        else{
        book.setName(name);
        System.out.println("Enter book author: ");
        book.setAuthor(s.nextLine());
        System.out.println("Enter book publisher: ");
        book.setPublisher(s.nextLine());
        System.out.println("Enter Collection address: ");
        book.setAddress(s.nextLine());
        System.out.println("Enter qty: ");
        book.setQty(s.nextInt());
        System.out.println("Enter price: ");
        book.setPrice(s.nextDouble());
        System.out.println("Enter borrowing copies: ");
        book.setBrwcopies(s.nextInt());
        database.AddBook(book);
        System.out.println("Book added successfully");
        user.menu(database, user);}
    }
}
