import java.util.*;
import java.sql.*;

public class Library {
    
    static final String JB_DRIVER = "org.h2.Driver";
    static final String JB_URL = "jdbc:h2:~/test";

    static final String JB_USER = "sa";
    static final String JB_PWD = "";

    private Scanner sc;
    private int lib_state = 0;
    
    private Connection conn;
    private Statement st;

    Library() throws ClassNotFoundException
    {
        sc = new Scanner(System.in);
        Class.forName(JB_DRIVER);

        try {
            conn = DriverManager.getConnection(JB_URL, JB_USER, JB_PWD);
            if(conn != null)
                System.out.println("Connected to the database");
            st = conn.createStatement();
            System.out.println(conn.getSchema());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Prints out the main menu
    public void menu() 
    {
        try{System.out.println("1. Access Students Database");
        System.out.println("2. Access Borrowed Database");
        System.out.println("3. Access Books Databases");
        System.out.println("Enter your choice: ");
        
        int choice = sc.nextInt();
        
        sc.nextLine();

        switch(choice)
        {
            case 1:
                students();
                break;
            case 3:
                books();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }

    }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void students()
    {
        try
        {
            System.out.println("STUDENTS DATABASE");
            System.out.println("1. Print Database");
            System.out.println("2. Add a new Student");
            System.out.println("Enter a choice:");
            
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice)
            {
                case 1:
                    ResultSet rs = st.executeQuery("select * from STUDENT");
                    System.out.println("ID  NAME");
                    while(rs.next())
                       System.out.println(rs.getInt("ID") + "  " + rs.getString("NAME"));
                    rs.close();
                    break;
                
                case 2:
                    System.out.println("Enter a name:");
                    String name = sc.nextLine();
                    int count = 1;
                    ResultSet rs_count = st.executeQuery("select * from STUDENT");
                    while(rs_count.next())
                    {
                        count++;
                    }
                    int rs1 = st.executeUpdate("insert into STUDENT(ID, NAME) VALUES(" + count + ",'" + name + "')");
                    System.out.println("No. of rows affected: " + rs1);
                    break;
                        
                default:
                    break;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void books()
    {
        try
        {
            ResultSet rs = st.executeQuery("select * from books");

            while(rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
            rs.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        //Checks the state and returns the respective function
        switch(lib_state)
        {
            case 0:
                menu();
                break;
            case 1:
                students();
                break;
            case 2:
                //borrowed();
                break;
            case 3:
                books();
                break;
            default:
                break;
        }
    }

    public void close() throws SQLException
    {
        st.close();
        conn.close();
    }
}
