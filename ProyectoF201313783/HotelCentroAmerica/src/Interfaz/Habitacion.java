package Interfaz;

public class Habitacion {
	int numero, edificio, nivel, camas, precio;
	String ubicacion;
	String sucursal, tipo, cliente;
	Habitacion siguiente;
	
	public Habitacion(int tipo){
		switch (tipo){
		case 1:
			precio = 200;
			camas = 1;
			break;
		case 2:
			precio = 250;
			camas = 2;
			break;
		case 3:
			precio = 379;
			camas = 4;
			break;
		default:
			precio = 0;
			camas =0;
			break;
		}
	}
	
	public Habitacion(String cliente, int numero, int tipo, String ubicacion){
		this.cliente = cliente; 
		this.numero = numero;
		switch (tipo){
		case 1:
			precio = 200;
			this.tipo="Sencilla";
			camas=1;
			break;
		case 2:
			precio = 250;
			this.tipo="Doble";
			camas=2;
			break;
		case 3:
			precio = 379;
			this.tipo="Suite-Delux";
			camas = 4;
			break;
		default:
			precio = 0;
			this.tipo = null;
			camas = 0;
			break;
		}
	}
	
}
