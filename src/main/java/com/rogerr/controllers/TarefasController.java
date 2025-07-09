package com.rogerr.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/tarefas")
@Tag(name = "Controle de tarefas", description = "Serviço para gerenciamento de dados de tarefas de agenda")
public class TarefasController {

	@PostMapping
	@Operation(summary = "Cadastro de tarefas", description = "Cria uma nova tarefa no sistema")
	public void post() {

	}

	@PutMapping
	@Operation(summary = "Edição de tarefas", description = "Atualiza uma tarefa existente no sistema")
	public void put() {

	}

	@DeleteMapping
	@Operation(summary = "Exclusão de tarefas", description = "Exclui uma tarefa no sistema")
	public void delete() {

	}

	@GetMapping
	@Operation(summary = "Consulta de tarefas", description = "Retorna as tarefas cadastradas no sistema")
	public void get() {

	}

}
