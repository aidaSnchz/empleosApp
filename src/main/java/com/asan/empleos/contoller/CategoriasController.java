package com.asan.empleos.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asan.empleos.model.Categoria;
import com.asan.empleos.service.ICategoriasService;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	
	//@RequestMapping(value="/index", method=RequestMethod.GET)
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Categoria> lista= serviceCategorias.buscarTodas();
		model.addAttribute("categorias", lista);
		return "categorias/listCategorias";
	}
	
	
	//@RequestMapping(value="/create", method=RequestMethod.GET)
	@GetMapping("/create")
	public String crear() {
	return "categorias/formCategoria";
	}
	
	 
	//@RequestMapping(value="/save", method=RequestMethod.POST)
	@PostMapping("/save")
	public String guardar(Categoria categoria,BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			// para que nos muestre los errores en consola
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
				}
			return "categorias/formCategoria";
		}
		serviceCategorias.guardar(categoria);
		//Solo esta disponible para este metodo, al hacer un rediret estamos haciendo otra peticion por lo que perderimos el mensaje
		//model.addAttribute("msg", "Registro guardado");
		attributes.addFlashAttribute("msg", "Registro Guardado");
		System.out.println("Categoria: " + categoria);
		//Redirecionamos al metodo para que nos recargue todos los datos
		return "redirect:/categorias/index"; 
	}


}
