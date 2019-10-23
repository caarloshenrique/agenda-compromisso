package br.com.agenda.executavel;

import br.com.agenda.view.AgendaView;
import java.sql.SQLException;

public class Executavel {
        public static void main(String[] args) throws SQLException {
        AgendaView av = new AgendaView();
        av.menu();
    }
}
