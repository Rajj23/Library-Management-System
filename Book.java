package library;

import java.util.ArrayList;

public class Book {
    
    private String name;
    private String author;
    private String publisher;
    private String adress;
    private String status;
    private int qty;
    private double price;
    private int brwcopies;

    public Book(){};

    public Book(String name, String author, String publisher, String adress, int qty, double price,int brwcopies){
        this.name=name;
        this.author=author;
        this.publisher=publisher;
        this.adress=adress;
        // this.status=status;
        this.qty=qty;
        this.price=price;
        this.brwcopies=brwcopies;
    }

    public String toString(){
        String text = "Book Name: " + name +"\n" +
            "Book Author: " + author +"\n" +
            "Book Publisher: " + publisher+ "\n" +
            "Book Collection Adress: " + adress+"\n" +
            "Qty: " + String.valueOf(qty) + "\n" +
            "Price: " +String.valueOf(price) + "\n" +
            "Borrowing Copies: " + String.valueOf(brwcopies);
            return text;

    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAddress() {
        return adress;
    }

    public String getStatus() {
        return status;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public int getBrwcopies() {
        return brwcopies;
    }
    

    // public void setBrwcopies(int brwcopies){
    //     this.brwcopies = brwcopies;
    // }

    
        public void setBrwcopies(int brwcopies) {
            this.brwcopies = brwcopies;
        }
    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAddress(String adress) {
        this.adress = adress;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // public String toString2(){
    //     String text =  name +"<N/>" + author + "<N/>" + publisher+ "<N/>"+ adress+ "<N/>" + String.valueOf(qty) + 
    //    "<N/>" +String.valueOf(price) + "<N/>" + String.valueOf(brwcopies);
    //     return text;
    // } 

    public String toString2() {
        return name + "<N/>" + author + "<N/>" + publisher + "<N/>" + adress + "<N/>" + 
               qty + "<N/>" + price + "<N/>" + brwcopies + "<N/>" + (status != null ? status : "Not Set");
    }
    
}
