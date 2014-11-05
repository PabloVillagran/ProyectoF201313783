package Interfaz;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Interfaz extends JFrame{
	Sucursal sucursal;
	String principal;
	File file=null;
	Integer[]dia=new Integer[31];
	Integer[]mes=new Integer[12];
	Integer[]aios=new Integer[10];
	int id;
	Cliente clientes;
	
	public Interfaz(){
		super("Hoteles de Centro América");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Interfaz.class.getResource("/resources/logo.png")));
		
		setSize(600, 600);
		setLayout(null);
		setLocationRelativeTo(null);
		
		JMenuBar mBar = new JMenuBar();
		JMenu hotel = new JMenu("Sucursal");
		JMenuItem abrir = new JMenuItem("Abrir Sucursal");
		abrir.addActionListener(new ActionListener(){
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e){
				JFileChooser fc = new JFileChooser("Seleccione el Archivo");
				if(fc.showOpenDialog(null) != fc.CANCEL_OPTION){
					if(fc.getSelectedFile().getPath().contains(".inicio")){
						file = fc.getSelectedFile();
						sucursal = new Sucursal(file);
					}else{
						errorDeCarga();
					}
				}
			}
		});
		JMenuItem crear = new JMenuItem("Nueva Sucursal");
		crear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser fc = new JFileChooser();
				String dir="";
				fc.setDialogTitle("Directorio de Destino");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setAcceptAllFileFilterUsed(false);
				if(fc.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
					dir = fc.getSelectedFile().toString();
				}
				String nombre = JOptionPane.showInputDialog("Nombre de la Sucursal","adsf");
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dir+"/"+nombre+".inicio")));
					bw.write("ubicacion:"+JOptionPane.showInputDialog("ubicacion:")+"\r\n");
					bw.write("habitaciones:"+JOptionPane.showInputDialog("Numero de Habitaciones:","552")+"\r\n");
					bw.write("moneda:"+JOptionPane.showInputDialog("moneda")+"\r\n"+
							"cPath:\r\nhPath:\r\npPath:\r\nsPath:\r\n");
					bw.flush();
					bw.close();
				} catch (IOException e1) {
					System.out.print(e1);
				}
				
			}
		});
		JMenuItem salir = new JMenuItem("Salir");
		salir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		JMenu reportes = new JMenu("Reportes");
		JMenuItem generar = new JMenuItem("Generar");
		
		JMenu promocion = new JMenu("Promocion");
		JMenuItem aLocales = new JMenuItem("Importar Locales");
		aLocales.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sucursal.cargarPromociones();
			}
		});
		JMenuItem aGlobales = new JMenuItem("Importar Globales");
		aGlobales.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sucursal.cargarGlobales();
			}
		});
		JMenuItem cLocales = new JMenuItem("Crear Promociones");
		cLocales.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				promociones();
			}
		});
		
		JMenu servicios = new JMenu("Servicios");
		JMenuItem restaurantes = new JMenuItem("Restaurantes");
		restaurantes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				servicios();
			}
		});
		JMenuItem mostrar = new JMenuItem("Mostrar todos");
		mostrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mostrarServicios();
			}
		});
		hotel.add(abrir);
		hotel.add(crear);
		hotel.addSeparator();
		hotel.add(salir);
		reportes.add(generar);
		promocion.add(aLocales);
		promocion.add(aGlobales);
		promocion.add(cLocales);
		servicios.add(restaurantes);
		servicios.add(mostrar);
		mBar.add(hotel);
		mBar.add(reportes);
		mBar.add(promocion);
		mBar.add(servicios);

		JPanel card = new JPanel();
		card.setBorder(null);
		card.setBounds(15, 15, 550, 425);
		card.setLayout(new CardLayout());
		
		//Panel Inicio
		JPanel pInicio=new JPanel();
		pInicio.setLayout(null);
		JLabel logo = new JLabel();
		ImageIcon icon = new ImageIcon();
		icon.setImage(Toolkit.getDefaultToolkit().getImage(Interfaz.class.getResource("/resources/hdc.png")));
		logo.setBounds(0,0,550,425);
		logo.setIcon(icon);
		pInicio.add(logo);
		
		//Panel Check-In
		JPanel pCIn = new JPanel();
		pCIn.setLayout(null);
		
		JLabel lContenedor = new JLabel();
		ImageIcon map = new ImageIcon();
		map.setImage(Toolkit.getDefaultToolkit().getImage(Interfaz.class.getResource("/resources/map1.jpg")));
		lContenedor.setIcon(map);
		pCIn.add(lContenedor);
		
		JTextField numero = new JTextField("Ingrese numero de reservacion");
		numero.setBounds(75,25,400,35);
		numero.setHorizontalAlignment(JTextField.CENTER);
		numero.addMouseListener(new MouseAdapter(){public void mouseClicked(MouseEvent e){numero.setText("");}});
		pCIn.add(numero);
		
		/*no implementado...
		JButton niveles[]=new JButton[3];
		int x=26,y=150,w=50,h=50;
		for(int i=0;i<3; i++){
		 	niveles[i] = new JButton("N"+(i+1));
			niveles[i].setBounds(x,y,w,h);
			niveles[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					JButton event = (JButton)e.getSource();
					String nivel = event.getActionCommand();
					/*if(nivel.contains("1")){
						map.setImage(Toolkit.getDefaultToolkit().getImage(Interfaz.class.getResource("/resources/map1.jpg")));
					}
					if(nivel.contains("2")){
						map.setImage(Toolkit.getDefaultToolkit().getImage(Interfaz.class.getResource("/resources/map2.jpg")));
					}
					if(nivel.contains("3")){
						map.setImage(Toolkit.getDefaultToolkit().getImage(Interfaz.class.getResource("/resources/map3.jpg")));
					}
					lContenedor.setIcon(map);
					construct(nivel, pCIn);				
				}
			});
			y+=70;
			niveles[i].setVisible(true);
		}
		pCIn.add(niveles[0]);pCIn.add(niveles[1]);pCIn.add(niveles[2]);
		lContenedor.setBounds(10,10,550,425);
		*/
		
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
		card.add(pInicio, "pInicio");
		card.add(pCIn,"Check-in");
		card.add(pCOut,"Check-out");
		CardLayout cardLayout = (CardLayout) card.getLayout();
		cardLayout.show(card, "pInicio");
		
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
		for(int i=0;i<=30;i++)dia[i]=i+1;
		for(int i=0;i<=11;i++)mes[i]=i+1;
		int temp=2014;
		for(int i=0;i<=9;i++){aios[i]= temp;temp++;}
		
		add(bReserv); add(checkIn); add(checkOut);
		add(card);
		setJMenuBar(mBar);
		setVisible(true);
	}
	
	protected void mostrarServicios() {
		
	}

	protected void construct(String nivel, JPanel panel) {
		int x=75,y=50,w=20,h=20;
		if(nivel.contains("N1")){
			JButton modulos[] = new JButton[6];
			for(int i=0;i<modulos.length;i++){
				modulos[i].setBounds(x,y,w,h);
				panel.add(modulos[i]);
			}
		}
		if(nivel.contains("N2")){
			JButton modulos[] = new JButton[7];
			for(int i=0;i<modulos.length;i++){
				
				panel.add(modulos[i]);
			}
		}
		if(nivel.contains("N3")){
			JButton modulos[] = new JButton[4];
			for(int i=0;i<modulos.length;i++){
				
				panel.add(modulos[i]);
			}		
		}
		class Action implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
			}
		}
	}

	public void errorDeCarga(){
		JOptionPane.showMessageDialog(this, "Error al cargar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
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
		
		lAIn = new JLabel("Año:");
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
		
		lAOut = new JLabel("Año:");
		lAOut.setBounds(407, 229, 35, 20);
		panel.add(lAOut);
		
		cAOut = new JComboBox(aios);
		cAOut.setBounds(445, 226, 62, 26);
		panel.add(cAOut);
		
		lTipoHabitacion = new JLabel("Tipo de Habitación");
		lTipoHabitacion.setBounds(36, 295, 133, 20);
		panel.add(lTipoHabitacion);
		
		String[]tipo={"","Sencilla","Doble","Suite Delux"};
		cTipoHabitacion = new JComboBox(tipo);
		cTipoHabitacion.setBounds(236, 292, 100, 26);
		panel.add(cTipoHabitacion);
		
		JLabel lCantidadHabitacion = new JLabel("Cantidad:");
		lCantidadHabitacion.setBounds(386, 295, 70,20);
		panel.add(lCantidadHabitacion);
		
		JTextField tCantidad = new JTextField();
		tCantidad.setBounds(445, 292, 62, 26);
		panel.add(tCantidad);
		
		lCHuesped = new JLabel("Cantidad de Huespedes");
		lCHuesped.setBounds(36, 361, 165, 20);
		panel.add(lCHuesped);
		
		tCHuesped = new JTextField();
		tCHuesped.setBounds(236, 358, 271, 26);
		panel.add(tCHuesped);
		tCHuesped.setColumns(10);
		
		//cargar!!
		lPromo = new JLabel("Promoción");
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
		bCancelar.setIcon(new ImageIcon(Interfaz.class.getResource("/resources/e.png")));
		bCancelar.setBounds(36, 527, 196, 60);
		bCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				reservacion.promt();
			}
		});
		panel.add(bCancelar);

		bGuardar = new JButton("Guardar");
		bGuardar.setIcon(new ImageIcon(Interfaz.class.getResource("/resources/g.png")));
		bGuardar.setBounds(311, 527, 196, 60);
		bGuardar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int fechaI = Integer.parseInt(cDiaIn.getSelectedItem().toString());
				int fechaF = Integer.parseInt(cDiaIn.getSelectedItem().toString());
				JDialog guardar = new JDialog(reservacion,"Guardar?");
				guardar.setBounds(0,0,250,250);
				guardar.setResizable(false);
				guardar.setLocationRelativeTo(null);
				guardar.setLayout(null);
				
				JTextArea display = new JTextArea();
				display.setBounds(7,15,143,179);
				display.setText("Nombre: "+tNombre.getText()+"\r\n"
						+"Check-In: "+cDiaIn.getSelectedItem()+"/"+cMesIn.getSelectedItem().toString()+"/"+cAIn.getSelectedItem().toString()
						+"\r\nCheck-out: "+cDiaOut.getSelectedItem().toString()+"/"+cMesOut.getSelectedItem().toString()+"/"+cAOut.getSelectedItem().toString()
						+"\r\n"+tCantidad.getText()+" habitaciones "+cTipoHabitacion.getSelectedItem().toString()+"\r\n"
						+"reserva para "+tCHuesped.getText()+" personas.");
						
				display.setEditable(false);
				display.setAutoscrolls(true);
				guardar.add(display);
				
				int s = JOptionPane.showConfirmDialog(null,"¿Es primera vez que nos visita?","Cliente",JOptionPane.YES_NO_OPTION);
				JButton ok = new JButton("OK");
				ok.setBounds(157,85,80,50);
				ok.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(s==JOptionPane.YES_OPTION){
							nuevoCliente(id,tNombre.getText(), cPais.getSelectedItem().toString(), sucursal.moneda);
							id++;
						}else{
							String id = JOptionPane.showInputDialog(null,"Ingrese su id");
							Cliente existe = buscarCliente(id);
							existe.reservar(cTipoHabitacion.getSelectedIndex());
						}
					}
				});
				
				guardar.add(ok);
				guardar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				guardar.setVisible(true);
			}
		});
		panel.add(bGuardar);
		
		reservacion.getContentPane().add(panel);
		reservacion.setVisible(true);
		reservacion.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	private void servicios(){
		InnerFrame service = new InnerFrame("Crear servicios");
		service.setBounds(0,0,600,600);
		service.setLocationRelativeTo(null);
		service.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		service.setVisible(true);
	}
	
	private void busqueda(){
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void promociones(){
		InnerFrame promos = new InnerFrame("Crear Promocion");
		promos.setBounds(0,0,435,700);
		promos.setLayout(null);
		promos.setResizable(false);
		promos.setLocationRelativeTo(null);
		promos.setParent(this);
		promos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel pPromos = new JPanel();
		pPromos.setBorder(new TitledBorder(null, "Promociones", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, null));
		pPromos.setBounds(15, 15, 396, 576);
		promos.add(pPromos);
		pPromos.setLayout(null);
		
		JLabel lDispo = new JLabel("Disponibilidad");
		lDispo.setBounds(25, 58, 100, 20);
		pPromos.add(lDispo);
		
		ButtonGroup rbGroup = new ButtonGroup();
		
		JRadioButton rbLocalDispo = new JRadioButton("Local");
		rbLocalDispo.setBounds(179, 54, 69, 29);
		pPromos.add(rbLocalDispo);
		rbGroup.add(rbLocalDispo);
		
		JRadioButton rbGlobalDispo = new JRadioButton("Global");
		rbGlobalDispo.setBounds(290, 54, 77, 29);
		pPromos.add(rbGlobalDispo);
		rbGroup.add(rbGlobalDispo);
		
		JLabel lNombre = new JLabel("Nombre");
		lNombre.setBounds(25, 121, 57, 20);
		pPromos.add(lNombre);
		
		JTextField tPromoName = new JTextField();
		tPromoName.setBounds(130, 118, 237, 26);
		pPromos.add(tPromoName);
		tPromoName.setColumns(10);
		
		JLabel lPrecio = new JLabel("Precio");
		lPrecio.setBounds(25, 182, 43, 20);
		pPromos.add(lPrecio);
		
		JSpinner sPrecio = new JSpinner();
		sPrecio.setBounds(267, 179, 100, 26);
		sPrecio.setModel(new SpinnerNumberModel(new Integer(50), null, null, new Integer(1)));
		pPromos.add(sPrecio);
		
		JLabel lFInicio = new JLabel("Fecha Inicio");
		lFInicio.setBounds(25, 240, 84, 20);
		pPromos.add(lFInicio);
		
		JLabel lFFin = new JLabel("Fecha Fin");
		lFFin.setBounds(25, 295, 66, 20);
		pPromos.add(lFFin);
		
		JComboBox cDInico = new JComboBox(dia);
		cDInico.setBounds(155, 237, 41, 26);
		pPromos.add(cDInico);
		
		JComboBox cMInicio = new JComboBox(mes);
		cMInicio.setBounds(211, 237, 41, 26);
		pPromos.add(cMInicio);
		
		JComboBox cAInicio = new JComboBox(aios);
		cAInicio.setBounds(267, 237, 65, 26);
		pPromos.add(cAInicio);
		
		JComboBox cDFin = new JComboBox(dia);
		cDFin.setBounds(155, 289, 41, 26);
		pPromos.add(cDFin);
		
		JComboBox cMFin = new JComboBox(mes);
		cMFin.setBounds(211, 289, 41, 26);
		pPromos.add(cMFin);
		
		JComboBox cAFin = new JComboBox(aios);
		cAFin.setBounds(267, 289, 65, 26);
		pPromos.add(cAFin);
		
		JList listA = new JList();
		listA.setBounds(25, 376, 162, 151);
		pPromos.add(listA);
		
		JButton bAgregar = new JButton("Agregar");
		bAgregar.setBounds(25, 531, 162, 29);
		pPromos.add(bAgregar);
		
		JList listQ = new JList();
		listQ.setBounds(205, 376, 162, 151);
		pPromos.add(listQ);
		
		JButton bQuitar = new JButton("Quitar");
		bQuitar.setBounds(205, 531, 162, 29);
		pPromos.add(bQuitar);
		
		JLabel lServicios = new JLabel("Servicios:");
		lServicios.setBounds(25, 340, 69, 20);
		pPromos.add(lServicios);
		
		JButton bGuardar = new JButton("Guardar");
		bGuardar.setBounds(215, 599, 190, 49);
		bGuardar.setIcon(new ImageIcon(Interfaz.class.getResource("/resources/g.png")));
		promos.add(bGuardar);
		promos.setVisible(true);
	}

	private void reportes(){
		
	}
	
	public void nuevoCliente(int id,String nombre, String paisResidencia, String moneda){
		Cliente nuevo = new Cliente(id,nombre, paisResidencia, moneda);
		nuevo.siguiente= clientes;
		clientes = nuevo;
	}
	
	public void removerCliente(String id){
		Cliente referencia = clientes;
		Cliente anterior = null;
		if(referencia != null){
			while(referencia != null){
				if(id == referencia.id){
					anterior.siguiente = referencia.siguiente;
				}else{
					anterior = referencia;
					referencia = referencia.siguiente;
				}
			}
		}else{
			System.out.println("no hay clientes en memoria");
		}
	}
	
	public Cliente buscarCliente(String id){
		Cliente referencia = clientes;
		Cliente resultado = null;
		while(referencia != null){
			if(referencia.id == id){
				resultado = referencia;
			}
			referencia = referencia.siguiente;
		}
		return resultado;
	}
	
	public class InnerFrame extends JFrame{
		JFrame parent = null;
		//JFrame thisFrame = this;
		public InnerFrame(String title){
			setTitle(title);
			addWindowListener(new WindowListener(){
				
				public void windowActivated(WindowEvent arg0) {
					
				}
				
				public void windowClosed(WindowEvent arg0) {
					
				}
				
				public void windowClosing(WindowEvent arg0) {
					promt();
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
		
		public void promt(){
			int result= JOptionPane.showConfirmDialog(this,"Seguro que quieres salir?","Seguro?",JOptionPane.ERROR_MESSAGE);
			if(result == JOptionPane.YES_OPTION){
				this.dispose();
				parent.setVisible(true);
			}else{
				System.out.println("No pasa nada.");
			}
		}
	}
	
	public static void main(String args[]){
		Interfaz inter = new Interfaz();
		inter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inter.setResizable(false);
	}
}
