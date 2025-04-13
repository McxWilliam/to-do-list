package com.to_do.tarefas.utils;

public class Menus {
    public void invocaMenu() {
        System.out.println("\n=== MENU DE TAREFAS ===");
        System.out.println("1. Criar nova tarefa");
        System.out.println("2. Listar tarefas existentes");
        System.out.println("3. Marcar como concluída");
        System.out.println("4. Remover tarefa");
        System.out.println("5. Ajuda");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // realizar integracao com chatGPT
    public void invocaAjuda() {
        System.out.println("-------------- MENU AJUDA --------------");
        System.out.printf("Olá! Como posso te ajudar?");
    }


}
