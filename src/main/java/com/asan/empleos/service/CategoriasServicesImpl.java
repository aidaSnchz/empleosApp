package com.asan.empleos.service;


import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.asan.empleos.model.Categoria;

@Service
public class CategoriasServicesImpl implements ICategoriasService {
	
	private List<Categoria> lista=null;
	
	
	public CategoriasServicesImpl() {
		lista = new LinkedList<Categoria>();
		
		Categoria categoria1= new Categoria();
		categoria1.setId(1);
		categoria1.setNombre("Ventas");
		categoria1.setDescripcion("Sector ventas");
		
		Categoria categoria2= new Categoria();
		categoria2.setId(2);
		categoria2.setNombre("Contabilidad");
		categoria2.setDescripcion("Sector Contabilidad");
		
		Categoria categoria3= new Categoria();
		categoria3.setId(3);
		categoria3.setNombre("Transporte");
		categoria3.setDescripcion("Sector Transporte");
		
		Categoria categoria4= new Categoria();
		categoria4.setId(4);
		categoria4.setNombre("Informatica");
		categoria4.setDescripcion("Sector Informatica");
		
		Categoria categoria5= new Categoria();
		categoria5.setId(5);
		categoria5.setNombre("Construccion");
		categoria5.setDescripcion("Sector Construccion");
		
		lista.add(categoria1);
		lista.add(categoria2);
		lista.add(categoria3);
		lista.add(categoria4);
		lista.add(categoria5);
	}

	public void guardar(Categoria categoria) {
		lista.add(categoria);
	}

	@Override
	public List<Categoria> buscarTodas() {
		
		return lista;
	}

	
	public Categoria buscarPorId(Integer idCategoria) {
		for(Categoria v: lista) {
			if(v.getId()==idCategoria) {
				return v;
			}
		}
		return null;
	}

}
