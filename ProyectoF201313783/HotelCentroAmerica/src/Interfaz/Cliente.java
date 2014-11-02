package Interfaz;

public class Cliente {
	public String nombre, paisResidencia, id;
	public int saldo;
	public String [] reservacion = new String[6];/*fechaI, fechaF, pais, tipoHabitacion, cantidadHabitacion, promo*/
	Pago pago;
	Cliente siguiente;
	
	public Cliente (String nombre,String paisResidencia,String id,String moneda){
		this.nombre = nombre;
		this.paisResidencia = paisResidencia;
		this.id = id;
	}

	public Cliente() {
		nombre = null;
		paisResidencia = null;
		id = null;
		siguiente = null;
	}

	public void reservar(int tipoHabitacion){
		
	}

	public void cancelarReservacion(){
		
	}

	public void comprarPaquete(){
		
	}

	public void comparPromocion(){
		
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
}
