import java.sql.Connection;
import java.sql.*;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kush
 */
public class mysql_setup {
    
    String mysql_password="kush";
    
    public static void main (String args[]){  
        
        mysql_setup obj=new mysql_setup();
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/","root", obj.mysql_password);
            Statement statement = con.createStatement();

            String query = "show databases;";
            
            ResultSet rs = statement.executeQuery(query);
            int flag = 1;
            
            while(rs.next()){
                String database = rs.getString("Database");

                if(database.equalsIgnoreCase("library_management")){
                    JOptionPane.showMessageDialog(null, "Database is Already Created...");
                    flag=0;
                    break;
                }
            }
            
            if(flag==1){
                String sql1="create database library_management;";
                String sql2="use library_management;";
                String sql3="create table books(ISBN varchar(5) primary key, Title char(25) not null, Author varchar(20) not null, Price int default 0, Quantity int default 0);";
                String sql4="CREATE TABLE Student(SID varchar(15) primary key, Name varchar(20) not null, Batch varchar(20) not null, Branch varchar(20) not null, Email varchar(30) not null, Mobile varchar(10));";
                String sql5="create table login_details(Role varchar(10), ID varchar(15), Password varchar(32));";
                String sql6="Insert into login_details values('admin', 'admin' , 'admin');";
                String sql7="CREATE TABLE Issue_Book (ISBN varchar(5), SID varchar(15),IssueDate date not null,foreign key Fk1(ISBN) references Books(ISBN),foreign key Fk2(SID) references Student(SID));";
                String sql8="CREATE TABLE Record (SID varchar(15),ISBN varchar(5), IssueDate date default null, ReturnDate date default null,foreign key Fk1(SID) references Student(SID), foreign key Fk2(ISBN) references Books(ISBN) );";

                statement.executeUpdate(sql1);
                statement.executeUpdate(sql2);
                statement.executeUpdate(sql3);
                statement.executeUpdate(sql4);
                statement.executeUpdate(sql5);
                statement.executeUpdate(sql6);
                statement.executeUpdate(sql7);
                statement.executeUpdate(sql8);
                JOptionPane.showMessageDialog(null,"Database and Table has been created successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);
            }
            statement.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}