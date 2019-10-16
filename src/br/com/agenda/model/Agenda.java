package br.com.agenda.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

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
        return id + " " + compromisso + " " + descricao + " " + localizacao;
    }
}
