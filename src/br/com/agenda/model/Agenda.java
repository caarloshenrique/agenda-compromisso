package br.com.agenda.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Agenda {
    private long id;
    private String compromisso;
    private String descricao;
    private LocalDateTime data;
    private String localizacao;
    
    @Override
    public String toString() {
        return id + " " + compromisso + " " + descricao + " " + formataData() + " " + localizacao;
    }
    
    public String formataData() {
        String st;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        st = df.format(data);
        return st;
    }
}
