package com.asan.empleos.contoller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.asan.empleos.model.Vacante;
import com.asan.empleos.service.IVacanteService;

@Controller
public class HomeController {
	//inyecion de dependencias
	@Autowired
	private IVacanteService serviceVacantes;
	
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista= serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		return "tabla";
	}
	/*public String mostrarTabla(Model model) {
		List<Vacante> lista= getVacantes();
		model.addAttribute("vacantes", lista);
		
		return "tabla";
	}*/
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero de telecomonicaciones");
		vacante.setDescripcion("Se necesita ingeniero para dar soporte a la intranet");
		vacante.setFecha(new Date());
		vacante.setSalario(9000.0);
		model.addAttribute("vacante", vacante);
		//retorna el nombre de la vista
		return "detalle";
		
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero de sistemas");
		lista.add("Auxiliar de Contabilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		
		model.addAttribute("empleos", lista);
		
		return "listado";
	}
	
	@GetMapping("/")
	public String mostrarHome(Model model){
		List<Vacante> lista= serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		return "home";
	}
	/*public String mostrarHome(Model model){
		/*model.addAttribute("mensaje", "Bienvenidos a empleos App");
		model.addAttribute("fecha", new Date());*/
		
		/*String nombre="auxiliar de contabilidad";
		Date fechaPub=new Date();
		double salario=9000;
		boolean vigente= true;
		
		model.addAttribute("nombre", nombre);
		model.addAttribute("fecha", fechaPub);
		model.addAttribute("salario", salario);
		model.addAttribute("vigente", vigente);
		
		
		return "home";
		
	}*/
	
	/**
	 * Método que regresa una lista de objetos de tipo Vacante
	 * @return
	 */
	private List<Vacante> getVacantes() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<Vacante> lista = new LinkedList<Vacante>();
		try {
			// Creamos la oferta de Trabajo 1.
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero Civil"); // Titulo de la vacante
			vacante1.setDescripcion("Solicitamos Ing. Civil para diseñar puente peatonal.");
			vacante1.setFecha(sdf.parse("08-02-2019"));
			vacante1.setSalario(8500.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("empresa1.png");
						
			// Creamos la oferta de Trabajo 2.
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Contador Publico");
			vacante2.setDescripcion("Empresa importante solicita contador con 5 años de experiencia titulado.");
			vacante2.setFecha(sdf.parse("09-02-2019"));
			vacante2.setSalario(12000.0);
			vacante2.setDestacado(0);
			vacante2.setImagen("empresa2.png");
			
			// Creamos la oferta de Trabajo 3.
			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Ingeniero Eléctrico");
			vacante3.setDescripcion("Empresa internacional solicita Ingeniero mecánico para mantenimiento de la instalación eléctrica.");
			vacante3.setFecha(sdf.parse("10-02-2019"));
			vacante3.setSalario(10500.0);
			vacante3.setDestacado(0);
			
			// Creamos la oferta de Trabajo 4.
			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Diseñador Gráfico");
			vacante4.setDescripcion("Solicitamos Diseñador Gráfico titulado para diseñar publicidad de la empresa.");
			vacante4.setFecha(sdf.parse("11-02-2019"));
			vacante4.setSalario(7500.0);
			vacante4.setDestacado(1);
			vacante4.setImagen("empresa3.png");
			
			/**
			 * Agregamos los 4 objetos de tipo Vacante a la lista ...
			 */
			lista.add(vacante1);			
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);

		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return lista;

	}

}
