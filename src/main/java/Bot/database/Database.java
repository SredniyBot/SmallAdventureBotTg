package Bot.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    public static void main(String[] args){
        String URL="jdbc:mysql://127.0.0.1:3306/players";
        String username="root";
        String password="Milioner2003!";
        try(Connection connection = DriverManager.getConnection(URL,username,password);
            Statement statement= connection.createStatement()){
            statement.executeUpdate("create table if not exists Stages (id int not null, text varchar(10000),img_path varchar (100),choices_text varchar (1000))");
            statement.executeUpdate("insert into Stages  set  id='1',text='привет, дружище, давно не виделись', img_path='src/main/sosi.png',choices_text='cho=<start>погладить кота~~~3<end>\n" +
                    "cho=<start>сказать что он милый~~~5<end>\n" +
                    "cho=<start>уйти~~~6<end>\n" +
                    "cho=<start>выгнать кота из дома~~~4<end>'");
            ResultSet rset=statement.executeQuery("select * from stages");

            while (rset.next()){
                System.out.println(rset.getInt("id"));
                System.out.println(rset.getString("text"));
                System.out.println(rset.getString("img_path"));
                System.out.println(rset.getString("choices_text"));

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }




}
