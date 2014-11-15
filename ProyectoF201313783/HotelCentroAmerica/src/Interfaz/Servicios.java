package Interfaz;

public class Servicios {
	String nombre, ubicacion, tipo;
	public float precio, ingresos;
	Servicios siguiente;
	int nclientes;
	
	public Servicios(String nombre, String ubicacion, String tipo, int nclientes, float ingresos){
		this.tipo = tipo;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.nclientes = nclientes;
		this.ingresos = ingresos;
	}

	public void asignarPrecio(float precio){
		this.precio = precio;
	}
	
	public void agregarNClientes(){
		nclientes++;
	}
	
	public void agregarIngresos(float cantidad){
		ingresos+=cantidad;
	}
	
	public void mantenimiento(float ingresos){
		
	}
	
}
