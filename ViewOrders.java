package library;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewOrders implements IOOperation{
    @Override
     public void oper(Database database,User user){
     Scanner s = new Scanner(System.in);
     System.out.println("Enter book name: ");
     String bookname = s.next();

     int i = database.getBook(bookname);
     if(i>-1){
         System.out.println("Book\t\tUser\t\tQty\tPrice");
        ArrayList<Order> orders = new ArrayList<Order>();
        for(Order order: database.getAllOrders()){
            if(order.getBook().getName().matches(bookname)){
                System.out.println(order.getBook().getName()+ "\t\t" + order.getUser().getName() +"\t\t" + order.getQty()+"\t"+ order.getPrice());
            }
        }
        System.out.println();
    }
    else{
            System.out.println("Book doesn't exist!\n");
    }
    user.menu(database, user);
    } 
}
