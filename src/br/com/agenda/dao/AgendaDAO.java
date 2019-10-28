package br.com.agenda.dao;

import br.com.agenda.model.Agenda;
import br.com.agenda.util.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgendaDAO {
    
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    private Connection getConnection(){
        if(conn == null)
            return ConexaoFactory.getConnection();
        else
            return conn;
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
            stmt.setTimestamp(3, Timestamp.valueOf(agenda.getData()));
            stmt.setString(4, agenda.getLocalizacao());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Agenda> listarAgenda() throws SQLException {
        List<Agenda> lista = new ArrayList<>();
        Agenda agenda;
        conn = getConnection();
        String sql = "SELECT * FROM agendatb"; 
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while(rs.next()){
            agenda = new Agenda();
            agenda.setId(rs.getInt(1));
            agenda.setCompromisso(rs.getString(2));
            agenda.setDescricao(rs.getString(3));
            agenda.setData(rs.getTimestamp(4).toLocalDateTime());
            agenda.setLocalizacao(rs.getString(5));
            lista.add(agenda);
        }
        
        return lista;
    }
    
    public Agenda buscaAgendaPorId(long id) throws SQLException {
       Agenda ag = new Agenda();
       conn = getConnection();
       String sql = "SELECT * FROM agendatb WHERE id = ?";
       stmt = conn.prepareStatement(sql);
       stmt.setLong(1, id);
       rs = stmt.executeQuery();
       if (rs.next()) {
           ag.setId(rs.getInt(1));
           ag.setCompromisso(rs.getString(2));
           ag.setDescricao(rs.getString(3));
           ag.setData(rs.getTimestamp(4).toLocalDateTime());
           ag.setLocalizacao(rs.getString(5));
       } else {
           ag = null;
       }
       return ag;
    }
    
    public Agenda buscaAgendaPorCompromisso(String compromisso) throws SQLException {
       Agenda ag = new Agenda();
       conn = getConnection();
       String sql = "SELECT * FROM agendatb WHERE compromisso LIKE ?";
       stmt = conn.prepareStatement(sql);
       stmt.setString(1, '%' + compromisso + '%');
       rs = stmt.executeQuery();
       if (rs.next()) {
           ag.setId(rs.getInt(1));
           ag.setCompromisso(rs.getString(2));
           ag.setDescricao(rs.getString(3));
           ag.setData(rs.getTimestamp(4).toLocalDateTime());
           ag.setLocalizacao(rs.getString(5));
       } else {
           ag = null;
       }
       return ag;
    }
    
    public boolean alterarCompromisso(Agenda agenda){
        conn = getConnection();
        String sql = "UPDATE agendatb SET compromisso = ? , descricao = ?, data = ?, localizacao = ? WHERE id = ?";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, agenda.getCompromisso());
            stmt.setString(2, agenda.getDescricao());
            stmt.setTimestamp(3, Timestamp.valueOf(agenda.getData()));
            stmt.setString(4, agenda.getLocalizacao());
            stmt.setLong(5, agenda.getId());
            stmt.executeUpdate();
            return true;
        } catch(SQLException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deletarCompromisso(long id) {
         conn = getConnection();
         String sql = "DELETE FROM agendatb WHERE id = ?";
         try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
             System.out.println("Deletado com sucesso!");
            return true;
         } catch(SQLException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
