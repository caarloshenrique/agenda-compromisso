package br.com.agenda.view;

import br.com.agenda.controller.AgendaController;
import br.com.agenda.model.Agenda;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AgendaView {
    private AgendaController agc;
    private Scanner sc;
    
    public AgendaView() {
        sc = new Scanner(System.in);
        agc = new AgendaController();
    }
    
    public void agendarCompromisso() {
        System.out.println("----Cadastro de Compromisso----");
        sc.nextLine();
        System.out.println("Entre com o compromisso:");
        agc.getAgenda().setCompromisso(sc.nextLine());
        //agc.cadastraCompromisso(sc.nextLine());
        System.out.println("Descreva o compromisso:");
        agc.getAgenda().setDescricao(sc.nextLine());
        System.out.println("Entre com a data: (Ex: 22/03/1989 10:50)");
        String st = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        agc.getAgenda().setData(LocalDateTime.parse(st, formatter));
        System.out.println("Entre com o local:");
        agc.getAgenda().setLocalizacao(sc.nextLine());
        if (agc.salvarAgenda()) {
            System.out.println("Agenda salva com sucesso!");
        } else {
            System.out.println("Erro ao tentar salvar agenda!");
        }
    }
    
    public void listarCompromissos() throws SQLException {
        agc.listarAgenda();
        if(agc.getLista().isEmpty()) {
            System.out.println("Lista Vazia!");
        } else {
            for (Agenda agenda : agc.getLista()) {
                System.out.println(agenda);
            }
        }
    }
    
    public void buscarPorId() throws SQLException {
        System.out.println("Digite o id do agendamento:");
        long id = sc.nextLong();
        agc.buscarAgendaPorId(id);
        System.out.println(agc.getAgenda());
    }
    
    public void alterarCompromisso() throws SQLException {
        System.out.println("----Edição de Compromisso----");
        buscarPorId();
        sc.nextLine();
        System.out.println("Compromisso:");
        agc.getAgenda().setCompromisso(sc.nextLine());
        System.out.println("Descrição:");
        agc.getAgenda().setDescricao(sc.nextLine());
        System.out.println("Data:");
        String st = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        agc.getAgenda().setData(LocalDateTime.parse(st, formatter));
        System.out.println("Local:");
        agc.getAgenda().setLocalizacao(sc.nextLine());
        if (agc.alterarCompromisso()) {
            System.out.println("Agenda atualizada com sucesso!");
        } else {
            System.out.println("Erro ao tentar salvar agenda!");
        }
    }
    
    public void deletarCompromisso() throws SQLException {
        System.out.println("----Exclusão de Compromisso----");
        agc.listarAgenda();
        System.out.println("Digite o id do agendamento:");
        long id = sc.nextLong();
        agc.buscarAgendaPorId(id);
        System.out.println(agc.getAgenda());
        agc.deletarCompromisso(id);
        agc.listarAgenda();
    }
    
    public void menu() throws SQLException{
        int opcao;
        
        do {
        System.out.println("----Agenda de Compromissos----");
        System.out.println("1-Agendar");
        System.out.println("2-Listar");
        System.out.println("3-Atualizar");
        System.out.println("4-Remover");
        System.out.println("5-Buscar por id");
        System.out.println("0-Sair");
        opcao = sc.nextInt();
        switch(opcao) {
            case 1:
                agendarCompromisso();
                break;
            case 2:
                listarCompromissos();
                break;
            case 3:
                alterarCompromisso();
                break;
            case 4:
                deletarCompromisso();
                break;
            case 5:
                buscarPorId();
                break;
            case 0:
                System.out.println("Tchau! Até mais!!!");
                break;
            default:
                System.out.println("Opção Inválida!");
        }
        } while(opcao != 0);
        System.exit(0);
   }
}
