public class App {

    public static void clear()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    public static void main(String[] args) throws Exception {
        
        clear();
        System.out.println("LIBRARY MANAGEMENT SYSTEM");
        System.out.println("By Sam Biswas");

        Library library = new Library();
        library.run();
    }
}
