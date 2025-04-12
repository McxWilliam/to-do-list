package com.to_do.tarefas.service;

import com.to_do.tarefas.model.Tarefa;
import org.springframework.stereotype.Service;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TarefaService {
    private List<Tarefa> tarefas = new ArrayList<>();
    private int proximoId = 1;

    public void adicionarTarefa(String descricao) {
        Tarefa novaTarefa = new Tarefa(proximoId++, descricao);
        tarefas.add(novaTarefa);
    }

    public List<Tarefa> listarTarefas() {
        return tarefas;
    }

    public boolean concluirTarefa(int id) {
        for (Tarefa t : tarefas) {
            if (t.getId() == id) {
                t.marcarComoConcluida();
                return true;
            }
        }
        return false;
    }

    public boolean removerTarefa(int id) {
        return tarefas.removeIf(t -> t.getId() == id);
    }

   public boolean listaTarefasVazia() {
        return tarefas.equals(Collections.emptyList());
   }
}
