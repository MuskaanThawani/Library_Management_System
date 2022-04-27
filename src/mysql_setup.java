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

                String sql9="Insert into Books values(\"B101\",\"Harry Potter\",\"Jk Rowling\",800,2);";
                String sql10="Insert into Books values(\"B102\",\"If I die today\",\"Sashi desh pandey\",950,3);";
                String sql11="Insert into Books values(\"B103\",\"Gone girl\",\"Gillian Flynn\",700,1);";
                String sql12="Insert into Books values(\"B104\",\"Ikigai\",\"Murakami\",1000,5);";
                String sql13="Insert into Books values(\"B105\",\"Percy jackson\",\"Rick Riordan\",500,3);";
                String sql14="Insert into Books values(\"B106\",\"Hunger Games\",\"Suzanne Collins\",680,4);";
                String sql15="Insert into Books values(\"B107\",\"Divergent\",\"Veronica Roth\",950,3);";
                String sql16="Insert into Books values(\"B108\",\"The Hobit\",\"JRR Tolkien\",800,2);";
                String sql17="Insert into Books values(\"B109\",\"Rich Dad Poor Dad\",\"Robert Kiyosaki\",1200,3);";
                
                statement.executeUpdate(sql1);
                statement.executeUpdate(sql2);
                statement.executeUpdate(sql3);
                statement.executeUpdate(sql4);
                statement.executeUpdate(sql5);
                statement.executeUpdate(sql6);
                statement.executeUpdate(sql7);
                statement.executeUpdate(sql8);
                statement.executeUpdate(sql9);
                statement.executeUpdate(sql10);
                statement.executeUpdate(sql11);
                statement.executeUpdate(sql12);
                statement.executeUpdate(sql13);
                statement.executeUpdate(sql14);
                statement.executeUpdate(sql15);
                statement.executeUpdate(sql16);
                statement.executeUpdate(sql17);
                
                JOptionPane.showMessageDialog(null,"Database and Table has been created successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);
            }
            statement.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}