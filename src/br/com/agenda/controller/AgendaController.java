package br.com.agenda.controller;

import br.com.agenda.dao.AgendaDAO;
import br.com.agenda.model.Agenda;
import java.sql.SQLException;

public class AgendaController {
    private Agenda agenda;
    private AgendaDAO dao;
    
    public AgendaController() {
        dao = new AgendaDAO();
        novo();
    }
    
    public Agenda getAgenda() {
        return agenda;
    }
    
    public void setAgenda() {
        this.agenda = agenda;
    }
    
    public void novo() {
        agenda = new Agenda();
    }
    
    public boolean salvarAgenda() {
        return dao.salvarAgenda(agenda);
    }
    
    //public void cadastraCompromisso(String st) {
    //    this.agenda.setCompromisso(st);
    //}
    
    public void sair() throws SQLException {
        dao.closeConnections();
    }
}
