package Interfaz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

public class Sucursal {
	String ubicacion, moneda, habitPath;
	int nhabitaciones;
	File habitfile;
	BufferedReader br;
	BufferedWriter bw;
	Habitacion habitaciones;
	
	public Sucursal(File file){
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null){
				if(line.contains("ubicacion")){
					ubicacion = line.substring(line.indexOf(":")+1);
				}
				if(line.contains("habitaciones")){
					nhabitaciones = Integer.valueOf(line.substring(line.indexOf(":")+1));
				}
				if(line.contains("moneda")){
					moneda = line.substring(line.indexOf(":")+1);
				}
				if(line.contains("hPath")){
					habitPath = line.substring(line.indexOf(":")+1);
					if(habitPath == ""){
						habitPath = "c://archivo.txt";
					}
				}
			}
			br.close();
			habitfile = new File(habitPath);
			if(!habitfile.exists()){
				System.out.println("No se encontro el archivo de habitaciones.");
				JFileChooser fc = new JFileChooser();
				while(!habitfile.exists()){
					fc.showOpenDialog(null);
					if(fc.getSelectedFile().getPath().contains(".habit")){
						habitfile = fc.getSelectedFile();
					}
				}
			}
			bw = new BufferedWriter(new FileWriter(file));
			bw.write("ubicacion:"+ubicacion+"\r\n"+"habitaciones:"+nhabitaciones+"\r\n"+"moneda:"+moneda+"\r\n"+"hPath:"+habitfile.getPath()+"\n");
			bw.flush();
			bw.close();
		}catch(IOException e){
			System.out.print(e);
		}
		agregarHabitacionesOcupadas(habitfile);
		System.out.println("sucursal creada");
	}
	
	public void checkIn(String idCliente,int numero, int cantidad, int tipo){
		try {
			File temp = new File("$.txt");
			br = new BufferedReader(new FileReader(habitfile));
			bw = new BufferedWriter(new FileWriter(temp));
			String line="";
			if(!habitacionOcupada(numero)){
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
	
	/*public static void main(String args[]){
		Sucursal sucursal = new Sucursal(new File("C:/Users/PabloDaniel/Documents/GitHub/ProyectoF201313783/ProyectoF201313783/HotelCentroAmerica/a.inicio"));
		sucursal.checkIn("0000", 104, 1, 1);
		sucursal.checkIn("2861", 205, 2, 1);
		sucursal.checkIn("2861", 206, 2, 1);
		sucursal.checkIn("7896", 440, 1, 3);
		sucursal.checkOut("2861");
		sucursal.printHabitaciones();
		System.out.println("done");
	}*/
	
}
