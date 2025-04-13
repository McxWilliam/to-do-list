package com.to_do.tarefas.runner;

import ch.qos.logback.core.joran.spi.HttpUtil;
import com.to_do.tarefas.service.ChatGPTService;
import com.to_do.tarefas.service.TarefaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.to_do.tarefas.utils.Menus;

import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.Scanner;

@Component
public class AppRunner implements CommandLineRunner {

    private final TarefaService tarefaService;
    private final ChatGPTService chatGPTService;
    private final Menus menu = new Menus();

    public AppRunner(TarefaService tarefaService, ChatGPTService chatGPTService) {
        this.tarefaService = tarefaService;
        this.chatGPTService = chatGPTService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        try {
            while (opcao != 0) {
                menu.invocaMenu();
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Descrição da tarefa: ");
                        String desc = scanner.nextLine();
                        tarefaService.adicionarTarefa(desc);
                        System.out.println("Tarefa criada!");
                    }
                    case 2 -> {
                        System.out.println("\n--- Tarefas ---");
                        tarefaService.listarTarefas().forEach(System.out::println);
                        if(tarefaService.listaTarefasVazia()){
                            System.out.println("Nenhum registro encontrado.");
                        }
                    }
                    case 3 -> {
                        System.out.print("ID da tarefa a concluir: ");
                        int id = scanner.nextInt();
                        if (tarefaService.concluirTarefa(id)) {
                            System.out.println("Tarefa concluída!");
                        } else {
                            System.out.println("Tarefa não encontrada.");
                        }
                    }
                    case 4 -> {
                        System.out.print("ID da tarefa a remover: ");
                        int id = scanner.nextInt();
                        if (tarefaService.removerTarefa(id)) {
                            System.out.println("Tarefa removida!");
                        } else {
                            System.out.println("Tarefa não encontrada.");
                        }
                    }
                    case 5 -> {
                        try{
                            menu.invocaAjuda();
                            String pergunta = scanner.nextLine();
                            String resposta = chatGPTService.perguntar(pergunta);
                            System.out.println(resposta);
                        } catch (Exception e){
                            System.out.println("Erro ao consultar a IA.");
                        }
                    }
                    case 0 -> System.out.println("Finalizando programa...");
                    default -> System.out.println("Opção inválida.");
                }
            }
            scanner.close();
        }
        catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado.");
        }

    }
}
