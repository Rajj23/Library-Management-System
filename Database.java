package library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {
    
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<String> usernames = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Borrowing> borrowings = new ArrayList<Borrowing>();


    private ArrayList<String> booknames = new ArrayList<String>();

private File userfile = new File("data/Users");
private File booksfile = new File("data/Books");
private File ordersfile = new File("data/Orders");
private File borrowingsfile = new File("data/Borrowings");



   public Database(){

    try {
       
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        
        if (!userfile.exists()) {
            userfile.createNewFile();
        }

    
        if (!booksfile.exists()) {
            booksfile.createNewFile();
        }

        if (!ordersfile.exists()) {
            ordersfile.createNewFile();
        }

        if (!borrowingsfile.exists()) {
            borrowingsfile.createNewFile();
        }
    } catch (Exception e) {
        System.err.println("Error creating files: " + e.getMessage());
    }

   
    getUsers();
    getBooks();
    getOrders();
    getBorrowings();
   }


    public void AddUser(User s){
        if(userExists(s.getName())) {
            System.out.println("Username not available. Please choose a different username.");
            return;  // Exit without adding the user
        }
        users.add(s);
        usernames.add(s.getName());
        saveUsers();
    }

    public int login(String phonenumber, String email){
        int n = -1;
        for(User s: users){
            if(s.getPhoneNumber().matches(phonenumber)&&s.getEmail().matches(email)){
                n=users.indexOf(s);
                break;
            }
        }
        return n;
    }

    public User getUser(int n){
        return users.get(n);
    }

    public void AddBook(Book book){
        books.add(book);
        booknames.add(book.getName());
        saveBooks();
    }

    private void getUsers(){
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader((userfile)));
            String s1;
            while((s1 = br1.readLine())!=null){
                text1 = text1 + s1;
            }
            br1.close();
        }catch (Exception e){
            System.err.println(e.toString());
        }

        if (!text1.isEmpty()) {
            String[] a1 = text1.split("<NewUser/>");
            for (String s : a1) {
                String[] a2 = s.split("<N/>");
                if (a2.length >= 4) { 
                    if (a2[3].matches("Admin")) {
                        User user = new Admin(a2[0], a2[1], a2[2]);
                        users.add(user);
                        usernames.add(user.getName());
                    } else {
                        User user = new NormalUser(a2[0], a2[1], a2[2]);
                        users.add(user);
                        usernames.add(user.getName());
                    }
                } else {
                    System.err.println("Invalid user data: " + s);
                }
            }
        }
    }

    private void saveUsers(){
        String text1 = "";
        for(User user: users){
            text1 = text1 + user.toString()+"<NewUser/>\n";
        }
        try{
            PrintWriter pw = new PrintWriter(userfile);
            pw.print(text1);
            pw.close();
        }catch (Exception e){
            System.err.println(e.toString());
        }
    }
    private void saveBooks(){
        String text1 = "";
        for(Book book: books){
            text1 = text1 + book.toString2()+"<NewBook/>\n";
        }
        try{
            PrintWriter pw = new PrintWriter(booksfile);
            pw.print(text1);
            pw.close();
        }catch (Exception e){
            System.err.println(e.toString());
        }
    }


    private void getBooks(){
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader((booksfile)));
            String s1;
            while((s1 = br1.readLine())!=null){
                text1 = text1 + s1;
            }
            br1.close();
            // System.out.println("Books file content:\n" + text1);
        }catch (Exception e){
            System.err.println(e.toString());
        }

        if (!text1.isEmpty()) {
            String[] a1 = text1.split("<NewBook/>");
            for (String s : a1) {
                Book book = parseBook(s.trim());
                if (book != null) {
                    books.add(book);
                    booknames.add(book.getName());
                } else {
                    System.err.println("Skipping invalid book entry: " + s);
                }
            }
            System.out.println("Books loaded: " + books.size());

        }
    }
    public Book parseBook(String s){
        try{
        String[] a= s.split("<N/>");
        if (a.length < 8) {
            System.err.println("Invalid book data: " + s);
            return null;
            // throw new IllegalArgumentException("Invalid book data: " + s);
            
        }
        Book book = new Book();
        book.setName(a[0]);
        book.setAuthor(a[1]);
        book.setPublisher(a[2]);
        book.setAddress(a[3]);
        book.setQty(Integer.parseInt(a[4]));
        book.setPrice(Double.parseDouble(a[5]));
        book.setBrwcopies(Integer.parseInt(a[6])); 
        book.setStatus(a[7]);
        return book;
    }catch(Exception e){
        System.err.println("Error parsing book: " +s + " - " + e.getMessage());
        return null;
    }
}

    public ArrayList<Book>  getAllBooks(){
        return books;
    }

    public int getBook(String bookname){
        // int i = -1;
        for(Book book: books){
            if(book.getName().equalsIgnoreCase(bookname.trim())){
                // i= books.indexOf(book);
                return books.indexOf(book);
            }
        }
    //     System.err.println("Book not found: " + bookname + ". Available books: ");
    // for (Book book : books) {
    //     System.err.println("- " + book.getName());
    // }
    return -1;

        // if (i == -1) {
        //     System.err.println("Book not found: " + bookname);
        // }
        // return i;
    }

    public Book getBook(int i){
        if (i >= 0 && i < books.size()) {
            return books.get(i);
        } else {
            throw new IndexOutOfBoundsException("Invalid book index: " + i);
        }
    }

    public void deleteBook(int i){
        books.remove(i);
        booknames.remove(i); 
        saveBooks();
    }

    public void DeleteAllData(){
        if (userfile.exists()) {
            try{
                userfile.delete();
            }
            catch(Exception e){}
        }

        // Create the Books file if it doesn't exist
        if (booksfile.exists()) {
            try{
                booksfile.delete();
            }
            catch(Exception e){};
        }

        if (ordersfile.exists()) {
            try{
                ordersfile.delete();
            }
            catch(Exception e){};
        }

        if (borrowingsfile.exists()) {
            try{
                borrowingsfile.delete();
            }
            catch(Exception e){};
        }
    }

    public void addOrder(Order order,Book book,int bookindex){
        orders.add(order);
        books.set(bookindex,book);
        saveOrders();
        saveBooks();
    }

    private void saveOrders(){
        String text1 = "";
        for(Order order: orders){
            text1 = text1 + order.toString2()+"<NewOrder/>\n";
        }
        try{
            PrintWriter pw = new PrintWriter(ordersfile);
            pw.print(text1);
            pw.close();
        }catch (Exception e){
            System.err.println(e.toString());
        }
    }

    private void getOrders(){
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader((ordersfile)));
            String s1;
            while((s1 = br1.readLine())!=null){
                text1 = text1 + s1;
            }
            br1.close();
        }catch (Exception e){
            System.err.println(e.toString());
        }

        if (!text1.isEmpty()) {
            String[] a1 = text1.split("<NewOrder/>");
            for (String s : a1) {
                Order order = parseOrder(s);
                orders.add(order);
            }

        }
    }

    public boolean userExists(String name){
        boolean f = false;
        for(User user: users){
            if(user.getName().equalsIgnoreCase(name)){
                f=true;
                break;
            }
        }
        return f;
    }

    private User getUserByName(String name){
        User u = new NormalUser("");
        for(User user: users){
            if(user.getName().equalsIgnoreCase(name.trim())){
                return user;
            }
        }
        System.err.println("User not found: " + name);
    return null;
    }

    private Order parseOrder(String s){
        String[] a = s.split("<N/>");
        Order order = new Order(books.get(getBook(a[0])) , getUserByName(a[1]), Double.parseDouble(a[2]), Integer.parseInt(a[3]));
        return order;
    }

    public ArrayList<Order> getAllOrders(){
        return orders;
    }

    private void saveBorrowings(){
        String text1 = "";
        for(Borrowing borrowing : borrowings){
            text1 = text1 + borrowing.toString2()+"<NewBorrowing/>\n";
        }
        try{
            PrintWriter pw = new PrintWriter(borrowingsfile);
            pw.print(text1);
            pw.close();
        }catch (Exception e){
            System.err.println(e.toString());
        }
    }

    private void getBorrowings(){
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader((borrowingsfile)));
            String s1;
            while((s1 = br1.readLine())!=null){
                text1 = text1 + s1;
            }
            br1.close();
        }catch (Exception e){
            System.err.println(e.toString());
        }

        if (!text1.isEmpty()) {
            String[] a1 = text1.split("<NewBorrowing/>");
            for (String s : a1) {
                Borrowing borrowing = parseBorrowing(s);
                borrowings.add(borrowing);
            }
        }
    }

    private Borrowing parseBorrowing(String s){
        String[] a = s.split("<N/>");
        if (a.length < 5) { // Ensure all required fields are present
            System.err.println("Invalid borrowing entry: " + s);
            return null;
        }
    
        // Extract and validate book name
        String bookName = a[3].trim();
        int bookIndex = getBook(bookName); // Debug the book name
        if(bookIndex==-1){
            System.err.println("Book not found for borrowing entry: " +a[3]);
            return null;
        }

        Book book = getBook(bookIndex);
        String userName = a[4].trim();
        User user = getUserByName(userName);

        if(user==null){
            System.err.println("User not found for borrowing entry: " +userName);
            return null;
        }
        // System.out.println("Book name: " + a[3] + ", Index: " + bookIndex);
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(a[0],formatter);
        LocalDate finish = LocalDate.parse(a[1],formatter);
        // int bookIndex = getBook(a[3]);
        Borrowing brw = new Borrowing(start,finish,getBook(bookIndex), user);
        return brw;
    }

    public void borrowBook(Borrowing brw, Book book,int bookindex){
        borrowings.add(brw);
        books.set(bookindex,book);
        saveBorrowings();
        saveBooks();
    }

    public ArrayList<Borrowing> getBrws(){
        return borrowings;
    }

    public void returnBook(Borrowing b, Book book,int bookindex){
        borrowings.remove(b);
        books.set(bookindex,book);
        saveBorrowings();
        saveBooks();
    }
}
