package com.rogerr.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rogerr.dtos.CategoriaResponseDto;
import com.rogerr.repositories.CategoriaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/categorias")
@Tag(name = "Controle de categorias", description = "Serviço para gerenciamento de dados de categorias.")
public class CategoriasController {

	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ModelMapper mapper;

	@GetMapping
	@Operation(summary = "Consulta de categorias", description = "Retorna todas as categorias cadastradas no sistema.")
	public List<CategoriaResponseDto> get() {

		var categorias = categoriaRepository.findAll();
		return categorias.stream().map(categoria -> mapper.map(categoria, CategoriaResponseDto.class))
				.collect(Collectors.toList());

	}

}
