package net.itinajero.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.util.Utileria;

@Controller
public class HomeController {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome(){
		return "home";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String buscar(@RequestParam("fecha") String fecha, Model model) {
		
		List<String> listaFechas = Utileria.getNextDays(4);
		List<Pelicula> peliculas = getLista();
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", fecha);
		model.addAttribute("peliculas", peliculas);
		
		System.out.println("Buscando todas las peliculas en exibicion para la fecha " + fecha);
		return "home";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Model model){
		
		List<String> listaFechas = Utileria.getNextDays(4);
		List<Pelicula> peliculas = getLista();
		//peliculas.add("Rapido y furioso");
		//peliculas.add("Toy Story 6");
		//peliculas.add("Los Increibles 3");
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
		model.addAttribute("peliculas", peliculas);
		return "home";
	}
	
	//@RequestMapping(value="/detail/{id}/{fecha}", method=RequestMethod.GET)
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	//public String mostrarDetalle(Model model,@PathVariable("id") int idPelicula,@PathVariable("fecha") String fecha) {
	public String mostrarDetalle(Model model,@RequestParam("idMovie") int idPelicula,@RequestParam("fecha") String fecha) {
		
		System.out.println("Buscando horarios para la pelicula " + idPelicula);
		System.out.println("Para la fecha " + fecha);
		
		//TO DO - BUSCAR EN LA BASE DE DATOS LOS HORARIOS
		/*
		 * String tituloPelicula = "Rapidos y Furiosos 2"; int duracion = 120; double
		 * precio = 50;
		 * 
		 * model.addAttribute("titulo", tituloPelicula); model.addAttribute("duracion",
		 * duracion); model.addAttribute("precio", precio);
		 */
		return "detalle";
	}
	
	//Metodo para generar una lista de Objetos de Modelo (Pelicula)
	private List<Pelicula> getLista(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		List<Pelicula> lista = null;
		try {
			lista = new LinkedList<>();
			
			Pelicula pelicula1 = new Pelicula();
			pelicula1.setId(1);
			pelicula1.setTitulo("Power Rangers 3");
			pelicula1.setDuracion(123);
			pelicula1.setClasificacion("B");
			pelicula1.setGenero("Accion");
			pelicula1.setFechaEstreno(formatter.parse("13-04-2019"));
			//imagen="cinema.png"
			//estatus="Activa"
			
			Pelicula pelicula2 = new Pelicula();
			pelicula2.setId(2);
			pelicula2.setTitulo("Que paso ayer 3");
			pelicula2.setDuracion(143);
			pelicula2.setClasificacion("B");
			pelicula2.setGenero("Comedia");
			pelicula2.setFechaEstreno(formatter.parse("15-04-2019"));
			pelicula2.setImagen("img2.png");
			
			Pelicula pelicula3 = new Pelicula();
			pelicula3.setId(3);
			pelicula3.setTitulo("Chucky");
			pelicula3.setDuracion(135);
			pelicula3.setClasificacion("C");
			pelicula3.setGenero("Terror");
			pelicula3.setFechaEstreno(formatter.parse("17-04-2019"));
			pelicula3.setImagen("img3.png");
			
			Pelicula pelicula4 = new Pelicula();
			pelicula4.setId(4);
			pelicula4.setTitulo("Titanic");
			pelicula4.setDuracion(151);
			pelicula4.setClasificacion("A");
			pelicula4.setGenero("Romance");
			pelicula4.setFechaEstreno(formatter.parse("21-04-2019"));
			pelicula4.setImagen("img4.png");
			pelicula4.setEstatus("Inactiva");
			
			Pelicula pelicula5 = new Pelicula();
			pelicula5.setId(5);
			pelicula5.setTitulo("Life: Vida Inteligente");
			pelicula5.setDuracion(100);
			pelicula5.setClasificacion("D");
			pelicula5.setGenero("Ciencia ficcion");
			pelicula5.setFechaEstreno(formatter.parse("22-04-2019"));
			pelicula5.setImagen("estreno5.png");
			pelicula5.setEstatus("Inactiva");
			
			lista.add(pelicula1);
			lista.add(pelicula2);
			lista.add(pelicula3);
			lista.add(pelicula4);
			lista.add(pelicula5);
			
			return lista;
		
		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
		
	}
	
}
