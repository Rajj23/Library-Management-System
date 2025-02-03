package library;

import java.util.ArrayList;

public  class ViewBooks implements IOOperation{
   @Override
   public void oper(Database database,User user){

      ArrayList<Book> books = database.getAllBooks();
      System.out.println("Name\t\tAuthor\t\tPublisher\t\tCLA\t\tQty\tPrice\t\tBorrowing copies");
      for(Book b:books){
        System.out.println(b.getName()+"\t"+b.getAuthor()+"\t\t"+b.getPublisher()+"\t\t"+b.getAddress()+"\t"+b.getStatus()+"\t"+b.getQty()+"\t"+b.getPrice()+"\t"+b.getBrwcopies());
      }
      user.menu(database, user);
   } 
}
