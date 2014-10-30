package asdf;

import javax.swing.JFrame;
import java.awt.Toolkit;

public class fsad extends JFrame {
	public fsad() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(fsad.class.getResource("/resources/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
	}
}
