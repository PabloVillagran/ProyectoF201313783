package Interfaz;

public class Cliente {
	String nombre, paisResidencia, id;
	int saldo;
	Pago pago;
	Cliente siguiente;
	
	public Cliente (String nombre,String paisResidencia,String id,String moneda){
		this.nombre = nombre;
		this.paisResidencia = paisResidencia;
		this.id = id;
	}
}
