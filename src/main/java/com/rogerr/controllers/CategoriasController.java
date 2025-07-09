package com.rogerr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rogerr.entities.Categoria;
import com.rogerr.repositories.CategoriaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/categorias")
@Tag(name = "Controle de categorias", description = "Serviço para gerenciamento de dados de categorias.")
public class CategoriasController {

	@Autowired
	CategoriaRepository categoriaRepository;

	@GetMapping
	@Operation(summary = "Consulta de categorias", description = "Retorna todas as categorias cadastradas no sistema.")
	public List<Categoria> get() {
		return categoriaRepository.findAll();
	}

}
