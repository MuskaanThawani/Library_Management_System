import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Books {
    mysql_setup obj=new mysql_setup();
    
    public String ISBN;
    public String Title;
    public String Author;
    public int Price;
    public int Quantity;
    
    private Connection con;
    private Statement statement;
    private ResultSet rs;
    
    public Books()
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_management","root", obj.mysql_password);
            statement = con.createStatement();
            String query="Select*from Books where Quantity>0;";
            rs=statement.executeQuery(query);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public Books(String ISBN,String Title,String Author,int Price,int Quantity){
        this();
        this.ISBN=ISBN;
        this.Title=Title;
        this.Author=Author;
        this.Price=Price;
        this.Quantity=Quantity;
    }
    
    public Books(String ISBN){
        this();
        try {
            String query="Select*from Books where ISBN='"+ISBN+"';";
            ResultSet rs = statement.executeQuery(query);
            
            while(rs.next()){
                this.ISBN=rs.getString("ISBN");
                this.Title=rs.getString("Title");
                this.Author=rs.getString("Author");
                this.Price=rs.getInt("Price");
                this.Quantity=rs.getInt("Quantity");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void save(){
        try {
            String query="Insert into Books values('"+ISBN+"','"+Title+"','"+Author+"',"+Price+","+Quantity+");";
            statement.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public boolean next(){
        try {
            if(rs.next()){
                this.ISBN=rs.getString("ISBN");
                this.Title=rs.getString("Title");
                this.Author=rs.getString("Author");
                this.Price=rs.getInt("Price");
                this.Quantity=rs.getInt("Quantity");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
