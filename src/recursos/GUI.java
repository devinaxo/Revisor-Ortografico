package recursos;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.text.*;

public class GUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextPane txtPane;
	private JButton btnEditar, btnGuardar, btnRevisar, btnModificar, btnAgregar;
	private JScrollPane scroll;
	private JComboBox<String> cBox;
	private JLabel label;
	private GridBagConstraints gbc;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuCargarDic, menuCargarTxt;
	private MyHighlightPainter pinturaRoja;
	private Revisor rev;

	public GUI(){
		pinturaRoja = new MyHighlightPainter(Color.red);
		rev = new Revisor();
		setTitle("Revisor Ortográfico");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarTxtPane();
		this.armarBotones();
		this.armarMenuCorrecion();
		this.armarMenu();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private void armarTxtPane(){
		this.txtPane = new JTextPane();
		txtEditable(false);
		this.txtPane.setPreferredSize(new Dimension(400, 250));
		this.txtPane.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		this.scroll = new JScrollPane(this.txtPane);
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.gbc.gridwidth = 3;
		this.gbc.gridheight = 3;
		this.gbc.weightx = 1.0;
		this.gbc.weighty = 1.0;
		this.gbc.fill = GridBagConstraints.BOTH;
		this.add(this.scroll, this.gbc);
	}
	private void armarBotones(){
		this.btnEditar = new JButton("Editar archivo");
		this.btnEditar.addActionListener(this);
		this.gbc.gridx = 0;// columna en la que inicia
		this.gbc.gridy = 4; // fila en la que inicia
		this.gbc.gridwidth = 1; // espacio que ocupa de manera horizontal
		this.gbc.gridheight = 1; // espacio que ocupa de manera vertical
		this.gbc.weightx = 1.0; // crecimiento en ancho
		this.gbc.weighty = 0.0; // crecimiento en alto
		this.gbc.fill = GridBagConstraints.HORIZONTAL; // como crece
		this.add(btnEditar, this.gbc);

		this.btnGuardar = new JButton("Actualizar");
		this.btnGuardar.addActionListener(this);
		this.gbc.gridx = 1;// columna en la que inicia
		this.gbc.gridy = 4; // fila en la que inicia
		this.gbc.gridwidth = 1; // espacio que ocupa de manera horizontal
		this.gbc.gridheight = 1; // espacio que ocupa de manera vertical
		this.gbc.weightx = 1.0; // crecimiento en ancho
		this.gbc.weighty = 0.0; // crecimiento en alto
		this.gbc.fill = GridBagConstraints.HORIZONTAL; // como crece
		this.add(btnGuardar, this.gbc);

		this.btnRevisar = new JButton("Revisar ortografía");
		this.btnRevisar.addActionListener(this);
		this.gbc.gridx = 2;// columna en la que inicia
		this.gbc.gridy = 4; // fila en la que inicia
		this.gbc.gridwidth = 1; // espacio que ocupa de manera horizontal
		this.gbc.gridheight = 1; // espacio que ocupa de manera vertical
		this.gbc.weightx = 1.0; // crecimiento en ancho
		this.gbc.weighty = 0.0; // crecimiento en alto
		this.gbc.fill = GridBagConstraints.HORIZONTAL; // como crece
		this.add(btnRevisar, this.gbc);

		this.btnModificar = new JButton("Modificar diccionario");
		this.btnModificar.addActionListener(this);
		this.btnModificar.setEnabled(false);
		this.gbc.gridx = 3;// columna en la que inicia
		this.gbc.gridy = 4; // fila en la que inicia
		this.gbc.gridwidth = 1; // espacio que ocupa de manera horizontal
		this.gbc.gridheight = 1; // espacio que ocupa de manera vertical
		this.gbc.weightx = 1.0; // crecimiento en ancho
		this.gbc.weighty = 0.0; // crecimiento en alto
		this.gbc.fill = GridBagConstraints.HORIZONTAL; // como crece
		this.add(btnModificar, this.gbc);
	}
	private void armarMenuCorrecion() {
		this.label = new JLabel("", SwingConstants.CENTER);
		this.gbc.gridx = 3;// columna en la que inicia
		this.gbc.gridy = 1; // fila en la que inicia
		this.gbc.gridwidth = 1; // espacio que ocupa de manera horizontal
		this.gbc.gridheight = 1; // espacio que ocupa de manera vertical
		this.gbc.weightx = 1.0; // crecimiento en ancho
		this.gbc.weighty = 1.0; // crecimiento en alto
		this.gbc.fill = GridBagConstraints.HORIZONTAL; // como crece
		this.add(this.label, this.gbc);

		this.cBox = new JComboBox<>();
		this.cBox.setVisible(false);
		this.gbc.gridx = 3;// columna en la que inicia
		this.gbc.gridy = 2; // fila en la que inicia
		this.gbc.gridwidth = 1; // espacio que ocupa de manera horizontal
		this.gbc.gridheight = 1; // espacio que ocupa de manera vertical
		this.gbc.weightx = 1.0; // crecimiento en ancho
		this.gbc.weighty = 1.0; // crecimiento en alto
		this.gbc.fill = GridBagConstraints.HORIZONTAL; // como crece
		this.add(this.cBox, this.gbc);

		this.btnAgregar = new JButton("Agregar a diccionario");
		this.btnAgregar.addActionListener(this);
		this.btnAgregar.setVisible(false);
		this.gbc.gridx = 3;// columna en la que inicia
		this.gbc.gridy = 3; // fila en la que inicia
		this.gbc.gridwidth = 1; // espacio que ocupa de manera horizontal
		this.gbc.gridheight = 1; // espacio que ocupa de manera vertical
		this.gbc.weightx = 1.0; // crecimiento en ancho
		this.gbc.weighty = 1.0; // crecimiento en alto
		this.gbc.fill = GridBagConstraints.HORIZONTAL; // como crece
		this.add(this.btnAgregar, this.gbc);
	}
	private void armarMenu() {
		menuBar = new JMenuBar();

		menu = new JMenu("Archivos");
		menuBar.add(menu);

		menuCargarDic = new JMenuItem("Cargar diccionario");
		menuCargarDic.addActionListener(this);
		menuCargarTxt = new JMenuItem("Cargar texto");
		menuCargarTxt.addActionListener(this);
		menuCargarDic.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
		menuCargarTxt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
		menu.add(menuCargarDic);
		menu.add(menuCargarTxt);

		this.gbc.gridx = 0;// columna en la que inicia
		this.gbc.gridy = 0; // fila en la que inicia
		this.gbc.gridwidth = 4; // espacio que ocupa de manera horizontal
		this.gbc.gridheight = 1; // espacio que ocupa de manera vertical
		this.gbc.weightx = 1.0; // crecimiento en ancho
		this.gbc.weighty = 0.0; // crecimiento en alto
		this.gbc.fill = GridBagConstraints.HORIZONTAL; // como crece

		this.add(menuBar, gbc);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == menuCargarDic) {
			abrirDiccionario();
		}
		if(e.getSource() == menuCargarTxt) {
			abrirTexto();
		}
		if(e.getSource() == btnEditar){
			editar();
		}
		if(e.getSource() == btnGuardar){
			guardar();
		}
		if(e.getSource() == btnRevisar) {
			revisar();
		}
		if(e.getSource() == btnModificar) {
			habilitar();
		}
		if(e.getSource() == btnAgregar) {
			actualizarDiccionario();
		}
	}
	private void abrirDiccionario() {
		rev.iniciarDiccionario();
		quitarHighlights();
		cBox.removeAllItems();
	}
	private void abrirTexto() {
		rev.iniciarTexto();
		cBox.removeAllItems();
		abrir();
	}
	private void abrir(){
		quitarHighlights();
		try{
			FileReader fr = new FileReader(rev.getDireccionArchivo()); 
			BufferedReader br = new BufferedReader(fr);
			this.txtPane.read(br, null);
			br.close();
		}catch(Exception e ){
			//JOptionPane.showMessageDialog(null, e);
		}
	}
	private void editar() {
		quitarHighlights();
		if(rev.getDireccionArchivo() != "") {
			txtEditable(true);
		}else
			JOptionPane.showMessageDialog(null,"No se ha cargado ningún texto","Error", JOptionPane.ERROR_MESSAGE);
	}
	private void guardar(){
		quitarHighlights();
		if(rev.getDireccionArchivo() != "") {
			try{
				FileWriter fw = new FileWriter(rev.getDireccionArchivo());
				BufferedWriter bw = new BufferedWriter (fw);
				this.txtPane.write(bw);
				bw.close();
				this.txtPane.setText(""); //hay que resetear el "buffer"
				abrir(); //antes de volver a cargar el archivo
				txtEditable(false);

			}catch(Exception e){
				//JOptionPane.showMessageDialog(null, e);
			}
		}else
			JOptionPane.showMessageDialog(null,"No se ha cargado el texto.","Error", JOptionPane.ERROR_MESSAGE);
	}
	private void revisar() {
		guardar();
		abrir();
		cBox.removeAllItems();
		rev.getPalabrasMal().limpiar(); //limpio el arraylist de anteriores palabras mal escritas
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cBox.getModel();
		if(rev.getDireccionDiccionario() != "" && rev.getDireccionArchivo() != "") {
			try {
				rev.www(rev.getTrie(), new FileInputStream(rev.getDireccionArchivo()));
				for(int i = 0; i < rev.getPalabrasMal().tamanio(); i++) {
					highlight((String) rev.getPalabrasMal().devolver(i));
					if(!existe(model, (String) rev.getPalabrasMal().devolver(i))) { //agrego un item si y solo si no se encuentra ya en el combobox
						model.addElement((String) rev.getPalabrasMal().devolver(i));
					}
					cBox.repaint(); //recargo el combobox con el cambio
					txtEditable(false);
					this.btnModificar.setEnabled(true);
				}
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
			}
		}else {
			if(rev.getDireccionDiccionario() == "") {
				JOptionPane.showMessageDialog(null,"No se ha cargado el diccionario.","Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null,"No se ha cargado el archivo a corregir.","Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private boolean existe(DefaultComboBoxModel<String> model, String word) {
		for (int i = 0; i < model.getSize(); i++) {
			String item = model.getElementAt(i);
			if (word.equals(item)) {
				return true;
			}
		}
		return false;
	}
	private void highlight(String busc) {
		try {
			Highlighter hilite = txtPane.getHighlighter(); //creo un subrayador que actúe sobre mi textpane
			String text = txtPane.getStyledDocument().getText(0, txtPane.getStyledDocument().getLength()); //obtengo el texto de mi txtPane
						
			int pos = 0;
			text = text.toUpperCase(); //cambio ambos textos a mayúsculas para la comparación
			busc = busc.toUpperCase();
			
			Pattern word = Pattern.compile("\\b" + busc + "\\b"); //creo un patrón de la palabra a buscar con delimitadores de palabra \b par que busque
																								//específicamente la palabra y no subcaracteres
			Matcher match = word.matcher(text); //interpreto el patrón con el texto del textpane
			while(match.find()) { //mientras encuentre la palabra
				try {
					pos = match.start(); //actualizo pos con el indice donde empieza mi palabra
					hilite.addHighlight(pos, pos+busc.length(), pinturaRoja); //agrego el highlight
					pos += busc.length(); //actualizo pos sumandole el tamaño de mi palabra buscada
				}catch(Exception e) {
					
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void quitarHighlights() {
		Highlighter hilite = txtPane.getHighlighter();
		Highlighter.Highlight[] hilites = hilite.getHighlights();

		for (int i = 0; i < hilites.length; i++) {
			if (hilites[i].getPainter() instanceof MyHighlightPainter) {
				hilite.removeHighlight(hilites[i]);
			}
		}
	}
	private void habilitar() {
		this.label.setText("Palabras mal escritas");
		this.cBox.setVisible(true);
		this.btnAgregar.setVisible(true);
	}
	private void actualizarDiccionario() {
		try {
			FileWriter fw = new FileWriter(rev.getDireccionDiccionario(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			try {
				pw.println(cBox.getItemAt(cBox.getSelectedIndex()).toLowerCase()); //agrego la palabra al archivo txt del diccionario
				rev.getTrie().insertar(cBox.getItemAt(cBox.getSelectedIndex())); //inserto la palabra en el trie
				//rev.getTrie().mostrarTrie(); //muestro el trie para debug
				rev.getPalabrasMal().eliminar((cBox.getSelectedIndex())); //retiro la palabra agregada al diccionario del arraylist de palabras mal escritas

				DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cBox.getModel(); //obtengo el modelo del combobox
				model.removeElement(cBox.getItemAt(cBox.getSelectedIndex())); //remuevo el elemento seleccionado (la palabra agregada)
				
				revisar();
				
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Item seleccionado no es válido.","Error", JOptionPane.ERROR_MESSAGE);
			} finally {
			}
			cBox.repaint(); //actualizo el combobox
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void txtEditable(boolean valor) {
		if(valor) {
			txtPane.setEditable(true);
			txtPane.setBackground(Color.WHITE);
		}else {
			txtPane.setEditable(false);
			txtPane.setBackground(Color.GRAY);
		}
	}
}
