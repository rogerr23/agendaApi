package com.rogerr.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rogerr.dtos.TarefaRequestDto;
import com.rogerr.dtos.TarefaResponseDto;
import com.rogerr.entities.Tarefa;
import com.rogerr.repositories.CategoriaRepository;
import com.rogerr.repositories.TarefaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tarefas")
@Tag(name = "Controle de tarefas", description = "Serviço para gerenciamento de dados de tarefas de agenda")
public class TarefasController {

	@Autowired
	TarefaRepository tarefaRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ModelMapper mapper;

	@PostMapping
	@Operation(summary = "Cadastro de tarefas", description = "Cria uma nova tarefa no sistema")
	public TarefaResponseDto post(@RequestBody @Valid TarefaRequestDto request) {

		var categoria = categoriaRepository.findById(request.getCategoriaId())
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada. Verifique o ID informado."));

		var tarefa = mapper.map(request, Tarefa.class);
		tarefa.setId(UUID.randomUUID());
		tarefa.setCategoria(categoria);
		tarefaRepository.save(tarefa);

		return mapper.map(tarefa, TarefaResponseDto.class);

	}

	@PutMapping("{id}")
	@Operation(summary = "Edição de tarefas", description = "Atualiza uma tarefa existente no sistema")
	public TarefaResponseDto put(@PathVariable UUID id, @RequestBody @Valid TarefaRequestDto request) {

		if (!tarefaRepository.existsById(id))
			throw new IllegalArgumentException("Tarefa não encontrada para edição. Verifique o ID informado.");

		var categoria = categoriaRepository.findById(request.getCategoriaId())
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada. Verifique o ID informado."));

		var tarefa = mapper.map(request, Tarefa.class);
		tarefa.setId(id);
		tarefa.setCategoria(categoria);
		tarefaRepository.save(tarefa);

		return mapper.map(tarefa, TarefaResponseDto.class);

	}

	@DeleteMapping("{id}")
	@Operation(summary = "Exclusão de tarefas", description = "Exclui uma tarefa no sistema")
	public TarefaResponseDto delete(@PathVariable UUID id) {

		var tarefa = tarefaRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Tarefa não encontrada para exclusão. Verifique o ID informado."));
		tarefaRepository.delete(tarefa);

		return mapper.map(tarefa, TarefaResponseDto.class);

	}

	@GetMapping
	@Operation(summary = "Consulta de tarefas", description = "Retorna as tarefas cadastradas no sistema")
	public List<TarefaResponseDto> get() {

		var tarefas = tarefaRepository.findAll();
		return tarefas.stream().map(tarefa -> mapper.map(tarefa, TarefaResponseDto.class)).collect(Collectors.toList());

	}

	@GetMapping("{id}")
	@Operation(summary = "Consulta de tarefas pelo ID", description = "Retorna as tarefas cadastradas no sistema através do ID")
	public TarefaResponseDto getById(@PathVariable UUID id) {

		var tarefa = tarefaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada."));

		return mapper.map(tarefa, TarefaResponseDto.class);
	}

}
