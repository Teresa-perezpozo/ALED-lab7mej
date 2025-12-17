package es.upm.dit.aled.lab7;
@WebServlet("/baja")
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.util.List;

import jakarta.servlet.http.HttpServlet;

public class Baja extends HttpServlet {
public protected void doGet ( HttpRequest request, HttpResponse response) throws IOException,Exception{
	
	InputStream file = getServletContext().getResourceAsStream("/baja.html");
	InputStreamReader r1 = new InputStreamReader(file);
	BufferedReader html = new BufferedReader(r1);
	String pagina = " ", linea,opciones;
	
	while((linea = html.readLine()) != null) {
		pagina +=linea;
		}
	
//CAMBIAR OPCIONES
	PacienteRepository pr = (PacienteRepository) getServletContext().getAttribute("repo");
	List <Paciente > listapa= pr.getPacientes();
	for(Paciente p :listapa) {
		//estoy había q hacerlo con opciones html
		opciones += p.getNombre()+" , "+p.getNombre();
	}
//ESCRIBO FINALMENTE
	pagina= pagina.replace("<h2></h2>", opciones);
	PrintWriter out = response.getWriter();
	response.setContentType("text/html");
	out.print(pagina);
	out.close();
}
 protected void doPost ( HttpRequest request, HttpResponse response) throws IOException,Exception{
	
	
	String dnibus= request.getParameter("dni");
	String nombrebus = request.getParameter("nombre");
	String apellidobus = request.getParameter("apellido");
	Paciente pbus = new Paciente(dinbus,nombrebus, apellidobus);
	InputStream file = getServletContext().getResourceAsStream("/baja");
	InputStreamReader r1 = new InputStreamReader(file);
	BufferedReader html = new BufferedReader(r1);
	String pagina = " ", linea,opciones,respuesta;
	while((linea = html.readLine()) != null) {
		pagina +=linea;
		}
	PacienteRepository pr = (PacienteRepository) getServletContext().getAttribute("repo");
	List <Paciente > listapa= pr.getPacientes();
	if(pr.findByDni(dnibus)!=null) {
		pr.removePaciente(p);
		respuesta = "el paciente ya ha sido dado de baja";
	}else {
		respuesta = "el paciente no estaba en el sistema";

	}
	PacienteRepository pr = (PacienteRepository) getServletContext().getAttribute("repo");
	List <Paciente > listapa= pr.getPacientes();
	for(Paciente p :listapa) {
		opciones += p.getNombre()+" , "+p.getNombre();
	}
	pagina = pagina.replace("<h2></h2>", respuesta);
	PrintWriter out = response.getWriter();
	response.setContentType("text/html");
	out.print(pagina);
	out.close();
}
}
