package Interfaz;

public class Cliente {
	public String nombre, paisResidencia, id;
	public int saldo;
	public String [] reservacion = new String[6];/*fechaI, fechaF, pais, tipoHabitacion, cantidadHabitacion, promo*/
	Pago cuenta;
	Cliente siguiente;
	
	public Cliente (String nombre,String paisResidencia,String moneda){
		this.nombre = nombre;
		this.paisResidencia = paisResidencia;
		cuenta = new Pago(0, moneda);
		siguiente = null;
	}

	public Cliente() {
		nombre = null;
		paisResidencia = null;
		siguiente = null;
		cuenta = new Pago(0, null);
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
	
	public void getId(){
		
	}
	
}
