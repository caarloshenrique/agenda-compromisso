package br.com.agenda.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoFactory {
   public static Connection getConnection() {
       try {
           return DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "");
       } catch (SQLException ex) {
           Logger.getLogger(ConexaoFactory.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }
   }
   
   public static void main(String[] args) {
       if (null != getConnection())
           System.out.println("Conectado!");
   }
}
