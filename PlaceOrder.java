package library;

import java.util.Scanner;

public class PlaceOrder implements IOOperation {

    @Override
    public void oper(Database database,User user) {
        
        Order order = new Order();
        System.out.println("\nEnter book name: ");
        Scanner s = new Scanner(System.in);
        String bookname = s.nextLine();
        int i = database.getBook(bookname);
        if(i<=-1){
            System.out.println("Book not available or does not exist in the library.");
            user.menu(database, user);
            return;

        }
        
            Book book = database.getBook(i);
            order.setBook(book);
            order.setUser(user);
            System.out.println("Enter qty: ");
            int qty = s.nextInt();
            if(qty>book.getQty()){
                System.out.println("Order quantity exceeds available copies. Only " + book.getQty() + " copies available.");
            user.menu(database, user);
            return;
            }
            order.setQty(qty);
            order.setPrice(book.getPrice()*qty);
            int bookindex = database.getBook(book.getName());
            book.setQty(book.getQty()-qty); 
            database.addOrder(order,book,bookindex);
            System.out.println("Order placed successfully!\n");
        
        user.menu(database, user);
    }
    
}
