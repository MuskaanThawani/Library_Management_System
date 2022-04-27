import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Student {
    mysql_setup obj=new mysql_setup();
    
    public String sid;
    public String name;
    public String batch;
    public String branch;
    public String email;
    public String mobile;
    private String password;
    
    private Connection con;
    private Statement statement;
    private ResultSet rst;
    
    public Student()
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_management","root", obj.mysql_password);
            statement = con.createStatement();
            String query="Select S.SID,S.Name,S.batch,S.Branch,S.Email,S.Mobile,L.Password from login_details L,Student S where L.Id=S.Sid";
            rst=statement.executeQuery(query);

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public Student(String sid,String name,String batch,String branch,String email,String mobile){
        this();
        this.sid=sid;
        this.name=name;
        this.batch=batch;
        this.branch=branch;
        this.email=email;
        this.mobile=mobile;
        this.password="";
    }
    
    public Student(String sid){
        this();
        try {
            String query="Select*from Student where sid='"+sid+"';";
            ResultSet rs = statement.executeQuery(query);
            
            while(rs.next()){
                this.sid=rs.getString("SID");
                this.name=rs.getString("Name");
                this.batch=rs.getString("Batch");
                this.branch=rs.getString("Branch");
                this.email=rs.getString("email");
                this.mobile=rs.getString("mobile");
                this.password="";
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void save(){
        try {
            String query="Insert into student values('"+sid+"','"+name+"','"+batch+"','"+branch+"','"+email+"','"+mobile+"');";
            statement.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public boolean next(){
        try {
            if(rst.next()){
                this.sid=rst.getString("S.SID");
                this.name=rst.getString("S.Name");
                this.batch=rst.getString("S.Batch");
                this.branch=rst.getString("S.Branch");
                this.email=rst.getString("S.Email");
                this.mobile=rst.getString("S.Mobile");
                this.password=rst.getString("L.Password");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void setPassword(String s){
        password=s;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void addUser(){
        try {
            String query="Insert into login_details values('student','"+sid+"','"+password+"');";
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
