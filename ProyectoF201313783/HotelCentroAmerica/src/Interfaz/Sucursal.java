package Interfaz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Sucursal {
	String ubicacion, moneda, habitPath, cPath,serviPath,promoPath;
	int nhabitaciones;
	BufferedReader br;
	BufferedWriter bw;
	Habitacion habitaciones;
	Promociones pormos;
	Servicios servss;
	File habitfile,pfile,sfile;
	
	public Sucursal(File file){
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null){
				if(line.contains("ubicacion")){
					ubicacion = line.substring(line.indexOf("=")+1);
					if(ubicacion.contains("Guatemala"))moneda="Q";
					if(ubicacion.contains("El Salvador")^ubicacion.contains("Panama"))moneda ="$";
					if(ubicacion.contains("Honduras"))moneda="L";
					if(ubicacion.contains("Nicaragua"))moneda="CN";
					if(ubicacion.contains("Costa Rica"))moneda="CC";
					if(ubicacion.contains("Belice"))moneda="$B";
				}
				if(line.contains("habitaciones")){
					nhabitaciones = Integer.valueOf(line.substring(line.indexOf("=")+1));
				}
				if(line.contains("cPath")){
					cPath = line.substring(line.indexOf("=")+1);
					if(cPath == "")cPath=".";
				}
				if(line.contains("hPath")){
					habitPath = line.substring(line.indexOf("=")+1);
					if(habitPath == "")	habitPath = ".";
				}
				if(line.contains("pPath")){
					promoPath = line.substring(line.indexOf("=")+1);
					if(promoPath == "")	promoPath = ".";
				}
				if(line.contains("serviPath")){
					serviPath = line.substring(line.indexOf("=")+1);
					if(serviPath == "")	serviPath = ".";
				}
			}
			br.close();
			/*if(!(cfile = new File(cPath)).exists()){
				System.out.println("No se encontro el archivo de clients.");
				cPath = file.getParent().replace("\\","/")+"/"+ file.getName().replace(".inicio", "c.client");
				cfile = new File(cPath);
				bw = new BufferedWriter(new FileWriter(new File(cPath)));
				bw.write("");
				bw.flush();
				bw.close();
			}*/
			if(!(habitfile= new File(habitPath)).exists()){
				System.out.println("No se encontro el archivo de habitaciones.");
				habitPath = file.getParent().replace("\\","/")+"/"+ file.getName().replace(".inicio", "h.habit");
				habitfile= new File(habitPath);
				bw = new BufferedWriter(new FileWriter(new File(habitPath)));
				bw.write("");
				bw.flush();
				bw.close();
			}
			if(!(pfile= new File(promoPath)).exists()){
				System.out.println("No se encontro el archivo de Promociones.");
				promoPath= file.getParent().replace("\\","/")+"/"+ file.getName().replace(".inicio", "pL.promo");
				pfile = new File(promoPath);
				bw = new BufferedWriter(new FileWriter(new File(promoPath)));
				bw.write("");
				bw.flush();
				bw.close();
			}
			if(!(sfile= new File(serviPath)).exists()){
				System.out.println("No se encontro el archivo de servicios.");
				serviPath = file.getParent().replace("\\","/")+"/"+ file.getName().replace(".inicio", "s.servi");
				sfile = new File(serviPath);
				bw = new BufferedWriter(new FileWriter(new File(serviPath)));
				bw.write("Mayan Place_"+ubicacion+"_restaurante_0_0\r\n"
						+"Mayan Nights_"+ubicacion+"_restaurante_0_0\r\n"
						+"Mayan Grill_"+ubicacion+"_restaurante_0_0\r\n"
						+"Italian Noodles_"+ubicacion+"_restaurante_0_0\r\n"
						+"Mediiterranean Flavor_"+ubicacion+"_restaurante_0_0\r\n"
						+"Centro de Reuniones_"+ubicacion+"_convenciones_0_0\r\n"
						+"Dulce1_"+ubicacion+"_piscina_0_0\r\n"
						+"Dulce2_"+ubicacion+"_piscina_0_0\r\n"
						+"Dulce3_"+ubicacion+"_piscina_0_0\r\n"
						+"Dulce4_"+ubicacion+"_piscina_0_0\r\n"
						+"Salada1_"+ubicacion+"_piscina_0_0\r\n"
						+"Playa_"+ubicacion+"_bar_0_0\r\n"
						+"Lobby Bar_"+ubicacion+"_bar_0_0\r\n"
						+"Disco Bar_"+ubicacion+"_bar_0_0\r\n"
						+"MasterSnack_"+ubicacion+"_bar_0_0\r\n"
						+"Club ni�os_"+ubicacion+"_club_0_0\r\n"
						+"Enfermeria_"+ubicacion+"_enfermeria_0_0\r\n"
						+"Disco400_"+ubicacion+"_bar_0_0\r\n"
						+"Gym_"+ubicacion+"_gimnasio_0_0\r\n");
				bw.flush();
				bw.close();
			}
			bw = new BufferedWriter(new FileWriter(file));
			bw.write("ubicacion="+ubicacion+"\r\n"+"habitaciones="+nhabitaciones+
					"\r\n"+"cPath="+cPath+"\r\n"+"hPath="+habitPath+"\r\n"+"pPath="+
					promoPath+"\r\n"+"serviPath="+serviPath);
			bw.flush();
			bw.close();
		}catch(IOException e){
			System.out.print(e);
		}
		agregarHabitacionesOcupadas(habitfile);
		System.out.println("sucursal creada");
	}
	
	public void checkIn(String idCliente,String numero, int cantidad, int tipo){
		try {
			File temp = new File("$.txt");
			br = new BufferedReader(new FileReader(habitfile));
			bw = new BufferedWriter(new FileWriter(temp));
			String line="";
			int n = Integer.parseInt(numero);
			if(!habitacionOcupada(n)){
				bw.write(idCliente+"_"+numero+"_"+cantidad+"_"+tipo+"\r\n");
			}else{
				System.out.println(numero +" ocupada.");
			}				
			while((line = br.readLine())!=null){
					bw.write(line+"\r\n");
			}
			br.close();
			bw.flush();
			bw.close();
			
			habitfile.delete();
			temp.renameTo(habitfile);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void checkOut(String idCliente){
		File temp = new File("some.txt");
		try {
			br = new BufferedReader(new FileReader(habitfile));
			bw = new BufferedWriter(new FileWriter(temp));
			String line="";
			while((line=br.readLine())!=null){
				String sLine[]=line.split("_");
				if(!sLine[0].contains(idCliente)){
					bw.write(line+"\r\n");
				}
			}
			bw.flush();
			bw.close();
			br.close();
			
			habitfile.delete();
			temp.renameTo(habitfile);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public boolean habitacionOcupada(int numero){
		boolean ocupada = false;
		Habitacion referencia = habitaciones;
		while(referencia != null){
			if(referencia.numero == numero){
				ocupada=true;
			}
			referencia=referencia.siguiente;
		}
		return ocupada;
	}
	
	public void agregarHabitacionesOcupadas(File habitfile){
		Habitacion nuevo;
		habitaciones = null;
		try{
			br = new BufferedReader(new FileReader(habitfile));
			String line;
			while((line = br.readLine())!=null){
				nuevo = habitaciones;
				String lineS[] = line.split("_");
				if(lineS.length == 4){
					nuevo = new Habitacion(lineS[0], Integer.parseInt(lineS[1]), Integer.parseInt(lineS[3]), ubicacion);
					nuevo.siguiente = habitaciones;
					habitaciones = nuevo;
				}
			}
			br.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	public void desocuparHabitacion(int numero){
		if(habitaciones != null){
			Habitacion referencia=habitaciones;
			Habitacion anterior = null;
			while(referencia.siguiente != null){
				if(referencia.numero == numero){
					anterior.siguiente = referencia.siguiente;
				}else{
					anterior = referencia;
					referencia = referencia.siguiente;
				}
			}
		}else{
			System.out.println("No existen mas habitaciones en memoria");
		}
		agregarHabitacionesOcupadas(habitfile);
	}
	
	public void printHabitaciones(){
		Habitacion referencia = habitaciones;
		if(referencia!=null){
			while(referencia != null){
			System.out.println(referencia.cliente +" "+ referencia.numero+" "+referencia.tipo);
			referencia = referencia.siguiente;
			}
		}
	}

	public void cargarPromociones() {
		pormos = null;
		try {
			br = new BufferedReader(new FileReader(new File(promoPath)));
			String line;
			while((line=br.readLine())!=null){
				String[]datos = line.split("_");
				agregarPromo(datos[0],datos[1],datos[2],datos[3]);
			}
			
		} catch (IOException e) {	}
	}

	public void cargarGlobales() {
		try{
			br = new BufferedReader(new FileReader(new File("/resources/globales.promo")));
			String line;
			while((line=br.readLine())!=null){
				String[]datos=line.split("_");
				agregarPromo(datos[0],datos[1],datos[2],datos[3]);
			}
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	public void agregarPromo(String nombre, String fecha, String applyTo,String precio){
		Promociones nueva = new Promociones(nombre,fecha,applyTo,precio);
		nueva.siguiente=pormos;
		pormos = nueva;
	}
	
	public void cargarServicios(){
		servss = null;
		try{
			br = new BufferedReader(new FileReader(new File(serviPath)));
			String line;
			while((line=br.readLine())!=null){
				String[]datos=line.split("_");
				int numero = Integer.parseInt(datos[3]);
				float ingresos = Float.valueOf(datos[4]);
				agregarServicio(datos[0],datos[1],datos[2],numero, ingresos);
			}
			br.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	public void agregarServicio(String nombre,String ubicacion, String tipo,int ncliente,float ingresos){
		Servicios nuevo = new Servicios(nombre, ubicacion, tipo, ncliente,ingresos);
		nuevo.siguiente = servss;
		servss = nuevo;
	}
	
	public void subirServicios(){
		Servicios tmp = servss;
		try{
			bw = new BufferedWriter(new FileWriter(new File(serviPath)));
			if(tmp != null){
				while(tmp!= null){
					String line = tmp.nombre +"_"+tmp.ubicacion+"_"+tmp.tipo+"_"+tmp.nclientes+"_"+tmp.ingresos+"\r\n";
					bw.write(line);
					tmp=tmp.siguiente;
				}
			}else{
				System.out.println("NO HAY SERVICIOS!");
			}
			bw.flush();
			bw.close();
		}catch(IOException e){
			
		}
	}
	
	public Servicios buscarServicio(String nombre){
		Servicios resultado = null;
		Servicios referencia = servss;
		while(referencia != null){
			if(referencia.nombre.equals(nombre)){
				resultado = referencia;
			}
			referencia = referencia.siguiente;
		}
		return resultado;
	}
	
	public void agregarServicioHabitacion(Servicios s, int numero){
		Habitacion referencia = habitaciones;
		while(referencia != null){
			if(referencia.numero == numero){
				referencia.agregarServicios(s);
			}
			referencia = referencia.siguiente;
		}
	}
	
	public Habitacion buscarHabitacion(String numero){
		Habitacion resultado=null;
		Habitacion referencia=habitaciones;
		int n = Integer.parseInt(numero);
		while(referencia != null){
			if(referencia.numero == n){
				resultado = referencia;
			}
			referencia = referencia.siguiente;
		}
		return resultado;
	}

	public Promociones buscarPromo(String nombre) {
		Promociones resultado=null;
		Promociones referencia=pormos;
		while(referencia != null){
			if(referencia.nombre.equals(nombre)){
				resultado = referencia;
			}
			referencia = referencia.siguiente;
		}
		return resultado;
	}
	
}
