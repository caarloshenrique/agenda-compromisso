package br.com.agenda.view;

import br.com.agenda.controller.AgendaController;
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
    
    public static void main(String[] args) {
        AgendaView av = new AgendaView();
        av.menu();
    }
    
    public void menu(){
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
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
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
