package br.com.agenda.dao;

import br.com.agenda.model.Agenda;
import br.com.agenda.util.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgendaDAO {
    
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    private Connection getConnection(){
        if(conn == null)
            return conn;
        else
            return ConexaoFactory.getConnection();
    }
    
    public void closeConnections() throws SQLException {
        if(conn != null) conn.close();
        if(stmt != null) stmt.close();
        if(rs != null) rs.close();
    }
    
    public boolean salvarAgenda(Agenda agenda) {
        conn = getConnection();
        String sql = "INSERT INTO agendatb (compromisso, descricao, data, localizacao) VALUES (?,?,?,?)";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, agenda.getCompromisso());
            stmt.setString(2, agenda.getDescricao());
            stmt.setDate(3, java.sql.Date.valueOf(agenda.getData().toLocalDate()));
            stmt.setString(4, agenda.getLocalizacao());
            stmt.execute();
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Agenda> listarAgenda() throws SQLException {
        List<Agenda> lista = null;
        Agenda agenda = new Agenda();
        conn = getConnection();
        String sql = "SELECT * FROM agendatb"; 
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while(rs.next()){
            agenda.setId(rs.getInt(1));
            agenda.setCompromisso(rs.getString(2));
            agenda.setDescricao(rs.getString(3));
            Date data = rs.getDate(4);
       
            agenda.setData(rs.getDate(4));
            agenda.setLocalizacao(rs.getString(5));
        }
        
        return lista;
    }
}
