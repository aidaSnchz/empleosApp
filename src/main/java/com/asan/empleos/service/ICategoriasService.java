package com.asan.empleos.service;

import java.util.List;

import com.asan.empleos.model.Categoria;

public interface ICategoriasService {
	
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);

}
