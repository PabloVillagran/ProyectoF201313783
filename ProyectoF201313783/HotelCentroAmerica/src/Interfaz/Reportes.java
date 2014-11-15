package Interfaz;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFileChooser;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Reportes {
	String fecha, hora;
	String dir="";
	Document doc = new Document();
	
	public void estadoDeCuenta(Cliente cliente){
		try {
			if (cliente != null) {
				PdfWriter.getInstance(doc, new FileOutputStream(dir+
						"/estadoDeCuenta"+cliente.id+".pdf"));
				doc.open();
				Paragraph encabezado = new Paragraph();
				encabezado
						.add("Estado de Cuenta de " + cliente.nombre + "\r\n");
				Paragraph contenido = new Paragraph();
				contenido.add("\tTiene un saldo de: \t" + cliente.saldo
						+ "\r\t" + "\tHa gastado la cantidad de: \t"
						+ cliente.moneda + ". " + cliente.ingresos);
				Paragraph gracias = new Paragraph();
				gracias.add("\r\n Gracias por preferirnos!\r\nreporte generado el"
						+ fecha + " a las " + hora);
				doc.add(encabezado);
				doc.add(contenido);
				doc.add(gracias);
				doc.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void serviciosXHabitacion(Habitacion h){
		try {
			if (h != null) {
				PdfWriter.getInstance(doc, new FileOutputStream(dir+
						"/ServiciosxHabitacion.pdf"));
				doc.open();
				Paragraph encabezado = new Paragraph();
				encabezado.add("Servicios para la habitacion " + h.numero
						+ "\r\n");
				doc.add(encabezado);
				Paragraph contenido = new Paragraph();
				while (h.servicios != null) {
					contenido.add("\t" + h.servicios.tipo + " "
							+ h.servicios.nombre + "\t utilizado "
							+ h.servicios.nclientes + "\r\n");
					doc.add(contenido);
					h.servicios = h.servicios.siguiente;
				}
				Paragraph gracias = new Paragraph();
				gracias.add("\r\n Gracias por preferirnos!\r\nreporte generado el"
						+ fecha + " a las " + hora);
				doc.add(gracias);
				doc.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void promosPaquetes(Promociones p){
		try {
			if (p != null) {
				PdfWriter.getInstance(doc, new FileOutputStream(dir+
						"/PromosyPaquetes.pdf"));
				doc.open();
				Paragraph encabezado = new Paragraph();
				encabezado.add("Promociones y Paquetes " + p.applyTo + "\r\n");
				doc.add(encabezado);
				Paragraph contenido = new Paragraph();
				while (p != null) {
					contenido.add("\t Promocion: " + p.nombre + "\t Precio: "
							+ p.precio + "\t creado el " + p.fecha+"\r\n");
					p = p.siguiente;
				}
				doc.add(contenido);
				Paragraph gracias = new Paragraph();
				gracias.add("\r\n Gracias por preferirnos!\r\nreporte generado el"
						+ fecha + " a las " + hora);
				doc.add(gracias);
				doc.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ingresosxServicio(Servicios s){
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(dir+
					"/ingresos_servicio.pdf"));
			if (s != null) {
				doc.open();
				Paragraph encabezado = new Paragraph();
				encabezado.add("Ingresos por servicio para el hotel de "
						+ s.ubicacion + "\r\n");
				doc.add(encabezado);
				Paragraph contenido = new Paragraph();
				while (s != null) {
					contenido.add("\t Servicio: " + s.nombre + "\t Ingresos: "
							+ s.ingresos + "\r\n");
					s = s.siguiente;
				}
				doc.add(contenido);
				Paragraph gracias = new Paragraph();
				gracias.add("\r\n Gracias por preferirnos!\r\nreporte generado el"
						+ fecha + " a las " + hora);
				doc.add(gracias);
				doc.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Reportes(){
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Directorio de Destino");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);
		if(fc.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
			dir = fc.getSelectedFile().toString();
		}
		Calendar calendario = new GregorianCalendar();
		fecha = calendario.get(Calendar.DAY_OF_MONTH)+"/"+calendario.get(Calendar.MONTH)+"/"+calendario.get(Calendar.YEAR)+" ";
		hora = calendario.get(Calendar.HOUR)+":"+calendario.get(Calendar.MINUTE)+" ";
		if(+calendario.get(Calendar.AM_PM)==0)hora=hora+"am. ";
		if(+calendario.get(Calendar.AM_PM)==1)hora=hora+"pm. ";
	}
}
