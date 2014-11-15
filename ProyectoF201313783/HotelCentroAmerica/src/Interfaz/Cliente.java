package Interfaz;

public class Cliente {
	public String nombre, paisResidencia, id,moneda;
	public float ingresos;
	public float saldo=0;
	public String [] reservacion = new String[6];/*fechaI, fechaF, pais, tipoHabitacion, cantidadHabitacion, promo*/
	Pago cuenta;
	Cliente siguiente;
	
	public Cliente (String id,String nombre,String paisResidencia, String moneda, float saldo,float ingreso){
		this.id = id;
		this.nombre = nombre;
		this.paisResidencia = paisResidencia;
		this.ingresos = ingreso;
		this.saldo = saldo;
		this.moneda = moneda;
		cuenta = new Pago(0, moneda);
		siguiente = null;
	}

	public Cliente() {
		nombre = null;
		paisResidencia = null;
		siguiente = null;
		cuenta = new Pago(0, null);
	}

	public void gastar(float cantidad){
		ingresos+=cantidad;
	}
	
	public void setSaldo(float cantidad){
		saldo+=cantidad;
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
