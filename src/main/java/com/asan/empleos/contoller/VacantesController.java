package com.asan.empleos.contoller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asan.empleos.model.Vacante;
import com.asan.empleos.service.ICategoriasService;
import com.asan.empleos.service.IVacanteService;
import com.asan.empleos.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	//para coger la ruta de las imagenes
	@Value("${empleos.ruta.imagenes}")
	private String ruta;
	
	//inyecion de dependencias
	@Autowired
	private IVacanteService serviceVacantes;
	
	@Autowired
	private ICategoriasService serviceCategoria;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/create")
	public String crear(Vacante vacante, Model model) {
		model.addAttribute("categorias", serviceCategoria.buscarTodas());
		return "vacantes/formVacantes";
	}
	
	@PostMapping("/save")
	public String guardar(Vacante vacante,  BindingResult result, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart) {
		//Para que nos mantenga en el formulario si hay un error, utilizamos el control de errores de Data Binding
		if (result.hasErrors()) {
			// para que nos muestre los errores en consola
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
				}
			return "vacantes/formVacantes";
			}
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			//String ruta = "c:/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
				// Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			}
		}


		serviceVacantes.guardar(vacante);
		//Solo esta disponible para este metodo, al hacer un rediret estamos haciendo otra peticion por lo que perderimos el mensaje
		//model.addAttribute("msg", "Registro guardado");
		attributes.addFlashAttribute("msg", "Registro Guardado");
		System.out.println("Vacante: " + vacante);
		//Redirecionamos al metodo para que nos recargue todos los datos
		return "redirect:/vacantes/index"; 
		
	}
	
	/*@PostMapping("/save")
	public String guardar(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion, @RequestParam("estatus") String estatus,
			@RequestParam("fecha") String fecha, @RequestParam("destacado") int destacado, 
			@RequestParam("salario") String salario, @RequestParam("detalles") String detalles) {
		System.out.println("Nombre Vacante: " + nombre);
		System.out.println("Descripcion: " + descripcion);
		System.out.println("Estatus: " + estatus);
		System.out.println("Fecha Publicación: " + fecha);
		System.out.println("Destacado: " + destacado);
		System.out.println("Salario Ofrecido: " + salario);
		System.out.println("detalles: " + detalles);
		return "vacantes/listVacantes"; 
		
	}*/
	
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		System.out.println("IdVacantes:" +idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {		
		Vacante vacante = serviceVacantes.buscarPorId(idVacante);	
		System.out.println("Vacante: " + vacante);
		model.addAttribute("vacante", vacante);
				
		return "detalle";
	}
	/*public String verDetalle(@PathVariable("id") int idVacante, Model model) {
		System.out.println("IdVacantes:" +idVacante);
		model.addAttribute("idVacante", idVacante);
		return "vacantes/detalle";
	}*/
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	

}
