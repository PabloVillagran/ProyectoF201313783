package Interfaz;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import asdf.fsad;

@SuppressWarnings("serial")
public class Interfaz extends JFrame{
	Sucursal sucursal;
	String principal;
	File file=null;
	
	public Interfaz(){
		super("Hoteles de Centro Am�rica");
		setIconImage(Toolkit.getDefaultToolkit().getImage(fsad.class.getResource("/resources/logo.png")));
		
		setSize(600, 600);
		setLayout(null);
		setLocationRelativeTo(null);
		
		JMenuBar mBar = new JMenuBar();
		JMenu menu = new JMenu("Sucursal");
		JMenuItem hotel = new JMenuItem("Abrir Sucursal");
		hotel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser fc = new JFileChooser("Seleccione el Archivo");
				fc.showOpenDialog(null);
				file = fc.getSelectedFile();
				while(!file.exists() ||(fc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)){
					fc.showOpenDialog(null);
					if(fc.getSelectedFile().getPath().contains(".inicio")){
						file = fc.getSelectedFile();
					}else{
						errorDeCarga(true);
					}
				}
				sucursal = new Sucursal(file);
			}
		});
		JMenu reportes = new JMenu("Reportes");
		reportes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		JMenu promocion = new JMenu("Promocion");
		promocion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		JMenuItem salir = new JMenuItem("Salir");
		salir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		menu.add(hotel);
		menu.addSeparator();
		menu.add(salir);
		mBar.add(menu);
		mBar.add(reportes);
		mBar.add(promocion);

		JPanel card = new JPanel();
		card.setBorder(null);
		card.setBounds(15, 15, 550, 425);
		card.setLayout(new CardLayout());
		
		//Panel Inicio
		JPanel pInicio=new JPanel();
		pInicio.setLayout(null);
		
		//Panel Check-In
		JPanel pCIn = new JPanel();
		pCIn.setLayout(null);
		
		JLabel lContenedor = new JLabel("HOLA MUNDO");
		lContenedor.setBounds(50,50,100,100);
		pCIn.add(lContenedor);
		
		//Panel Check-Out
		JPanel pCOut = new JPanel();
		pCOut.setLayout(null);
		
		JLabel lNHabitacion = new JLabel("Numero de Habitacion");
		lNHabitacion.setBounds(15, 16, 166, 20);
		pCOut.add(lNHabitacion);
		
		JTextField tNHabitacion = new JTextField();
		tNHabitacion.setBounds(196, 13, 100, 26);
		pCOut.add(tNHabitacion);
		tNHabitacion.setColumns(10);
		
		JLabel lInfo = new JLabel("Informacion del Cliente");
		lInfo.setBounds(15, 88, 166, 20);
		pCOut.add(lInfo);
		
		JTextArea taInfo = new JTextArea();
		taInfo.setBounds(25, 133, 499, 239);
		pCOut.add(taInfo);
		
		JButton bConfirmar = new JButton("Confirmar Check-Out");
		bConfirmar.setBounds(343, 388, 192, 29);
		bConfirmar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//verificar el check in
				//halar los datos del cliente
				//quitar reservacion del archivo
			}
		});
		pCOut.add(bConfirmar);
		
		//Fin de cartas
		card.add(pInicio, "Inicio");
		card.add(pCIn,"Check-in");
		card.add(pCOut,"Check-out");
		
		JButton bReserv = new JButton("Reservacion");
		JButton checkIn = new JButton("Check In");
		JButton checkOut = new JButton("Check out");

		checkIn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CardLayout cardLayout = (CardLayout) card.getLayout();
				cardLayout.show(card, "Check-in");
			}
		});
		
		checkOut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CardLayout cardLayout = (CardLayout) card.getLayout();
				cardLayout.show(card, "Check-out");
			}
		});
		
		bReserv.setBounds(10, getHeight()-115,185,45);
		checkIn.setBounds(205, getHeight()-115,185,45);
		checkOut.setBounds(getWidth()-200, getHeight()-115,185,45);
		
		bReserv.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				reservacion();
			}
		});
		checkIn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		checkOut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		add(bReserv); add(checkIn); add(checkOut);
		add(card);
		setJMenuBar(mBar);
		setVisible(true);
	}
	
	public void errorDeCarga(boolean error){
		if(error){
			JOptionPane.showMessageDialog(this, "Error al cargar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void reservacion(){
		InnerFrame reservacion = new InnerFrame("Reservaciones");
		reservacion.setBounds(0, 0, 600, 700);;
		reservacion.setLayout(null);
		reservacion.setResizable(false);
		reservacion.setLocationRelativeTo(null);
		reservacion.setParent(this);
		
		JLabel lNombre, lPais, lIn ,lOut,lTipoHabitacion, lCHuesped,lPromo,lCosto,lDiaIn,lDiaOut,lMesIn,lMesOut,lAIn,lAOut;
		JTextField tNombre,tCHuesped,tCosto;
		JComboBox cPais, cTipoHabitacion,cPromo,cDiaIn,cDiaOut,cMesIn,cMesOut,cAIn,cAOut;
		JButton bGuardar,bCancelar;
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Formulario de Reservacion", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(20, 32, 548, 596);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lNombre = new JLabel("Nombre y Apellido");
		lNombre.setBounds(36, 31, 133, 20);
		panel.add(lNombre);
		
		tNombre = new JTextField();
		tNombre.setBounds(236, 28, 271, 26);
		panel.add(tNombre);
		tNombre.setColumns(10);
		
		lPais = new JLabel("Pais de Residencia");
		lPais.setBounds(36, 97, 133, 20);
		panel.add(lPais);
		
		String[]pais={"","Africa","Argentina","Asia","Belice","Bolivia", "Brasil","Canada","Chile",
				"Cuba","Colombia","Costa Rica","Ecuador","El Salvador","Estados Unidos","Europa",
				"Guatemala","Honduras","Mexico","Nicaragua","Panama","Paraguay","Peru","Oceania","Uruguay","Venezuela"};
		cPais = new JComboBox(pais);
		cPais.setBounds(236, 94, 271, 26);
		panel.add(cPais);

		lIn = new JLabel("Fecha Check-In");
		lIn.setBounds(36, 163, 135, 20);
		panel.add(lIn);

		lOut = new JLabel("Fecha Check-Out");
		lOut.setBounds(36, 229, 129, 20);
		panel.add(lOut);
		
		Integer[]dia=new Integer[31];
		for(int i=0;i<=30;i++)dia[i]=i+1;
		Integer[]mes=new Integer[12];
		for(int i=0;i<=11;i++)mes[i]=i+1;
		Integer[]aios=new Integer[10];
		int temp=2014;
		for(int i=0;i<=9;i++){aios[i]= temp;temp++;}
		
		lDiaIn = new JLabel("Dia:");
		lDiaIn.setBounds(236, 163, 29, 20);
		panel.add(lDiaIn);
		
		cDiaIn = new JComboBox(dia);
		cDiaIn.setBounds(273, 160, 50, 26);
		panel.add(cDiaIn);
		
		lMesIn = new JLabel("Mes:");
		lMesIn.setBounds(328, 163, 33, 20);
		panel.add(lMesIn);
		
		cMesIn = new JComboBox(mes);
		cMesIn.setBounds(366, 160, 36, 26);
		panel.add(cMesIn);
		
		lAIn = new JLabel("A�o:");
		lAIn.setBounds(407, 163, 35, 20);
		panel.add(lAIn);
		
		cAIn = new JComboBox(aios);
		cAIn.setBounds(445, 160, 62, 26);
		panel.add(cAIn);
		
		lDiaOut = new JLabel("Dia:");
		lDiaOut.setBounds(236, 229, 29, 20);
		panel.add(lDiaOut);
		
		cDiaOut = new JComboBox(dia);
		cDiaOut.setBounds(273, 226, 50, 26);
		panel.add(cDiaOut);
		
		lMesOut = new JLabel("Mes:");
		lMesOut.setBounds(328, 229, 33, 20);
		panel.add(lMesOut);
		
		cMesOut = new JComboBox(mes);
		cMesOut.setBounds(366, 226, 36, 26);
		panel.add(cMesOut);
		
		lAOut = new JLabel("A�o:");
		lAOut.setBounds(407, 229, 35, 20);
		panel.add(lAOut);
		
		cAOut = new JComboBox(aios);
		cAOut.setBounds(445, 226, 62, 26);
		panel.add(cAOut);
		
		lTipoHabitacion = new JLabel("Tipo de Habitaci�n");
		lTipoHabitacion.setBounds(36, 295, 133, 20);
		panel.add(lTipoHabitacion);
		
		String[]tipo={"","Sencilla","Doble","Suite Delux"};
		cTipoHabitacion = new JComboBox(tipo);
		cTipoHabitacion.setBounds(236, 292, 271, 26);
		panel.add(cTipoHabitacion);
		
		lCHuesped = new JLabel("Cantidad de Huespedes");
		lCHuesped.setBounds(36, 361, 165, 20);
		panel.add(lCHuesped);
		
		tCHuesped = new JTextField();
		tCHuesped.setBounds(236, 358, 271, 26);
		panel.add(tCHuesped);
		tCHuesped.setColumns(10);
		
		//cargar!!
		lPromo = new JLabel("Promoci�n");
		lPromo.setBounds(36, 422, 76, 20);
		panel.add(lPromo);
		
		cPromo = new JComboBox();
		cPromo.setBounds(236, 419, 271, 26);
		panel.add(cPromo);

		lCosto = new JLabel("Costo");
		lCosto.setBounds(36, 488, 40, 20);
		panel.add(lCosto);
		
		tCosto = new JTextField();
		tCosto.setText("$ -");
		tCosto.setFont(new Font("Tahoma", Font.BOLD, 20));
		tCosto.setEditable(false);
		tCosto.setBounds(236, 485, 271, 26);
		panel.add(tCosto);
		
		bCancelar = new JButton("Cancelar");
		bCancelar.setIcon(new ImageIcon(fsad.class.getResource("/resources/e.png")));
		bCancelar.setBounds(36, 527, 196, 60);
		panel.add(bCancelar);

		bGuardar = new JButton("Guardar");
		bGuardar.setIcon(new ImageIcon(fsad.class.getResource("/resources/g.png")));
		bGuardar.setBounds(311, 527, 196, 60);
		panel.add(bGuardar);
		
		reservacion.getContentPane().add(panel);
		reservacion.setVisible(true);
		reservacion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void busqueda(){
		
	}
	
	private void promociones(){
		
	}

	private void reportes(){
		
	}
	
	public class InnerFrame extends JFrame{
		JFrame parent = null;
		public InnerFrame(String title){
			setTitle(title);
			addWindowListener(new WindowListener(){
				
				public void windowActivated(WindowEvent arg0) {
					
				}
				
				public void windowClosed(WindowEvent arg0) {
					
				}
				
				public void windowClosing(WindowEvent arg0) {
					parent.setVisible(true);
				}
				
				public void windowDeactivated(WindowEvent arg0) {
					
				}
				
				public void windowDeiconified(WindowEvent arg0) {
					
				}
				
				public void windowIconified(WindowEvent arg0) {
					
				}
				
				public void windowOpened(WindowEvent arg0) {
					
				}
				
			});
		}
		public void setParent(JFrame parent){
			this.parent = parent;
			this.setIconImage(parent.getIconImage());
		}
	}
	
	public static void main(String args[]){
		Interfaz inter = new Interfaz();
		inter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inter.setResizable(false);
	}
}
