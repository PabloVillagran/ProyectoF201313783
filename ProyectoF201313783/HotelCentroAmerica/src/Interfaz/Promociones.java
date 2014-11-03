package Interfaz;

public class Promociones {
	public String nombre,fecha, applyTo;
	public int applydays, precio, valid_ate;
	Servicios servicio;
	Promociones siguiente;
	
	public Promociones(String nombre, String fecha, String applyTo){
		this.nombre = nombre;
		this.fecha = fecha;
		this.applyTo =applyTo;
		siguiente = null;
	}
}
