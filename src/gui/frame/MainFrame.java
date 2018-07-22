package gui.frame;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import gui.panel.MainPanel;
import util.GUIUtil;

public class MainFrame extends JFrame{	
	public static MainFrame instance = new MainFrame();
	public MainFrame() {
		this.setSize(500, 450);
		this.setTitle("Ò»±¾ºýÍ¿ÕË");
		this.setLocationRelativeTo(null);
		this.setContentPane(MainPanel.instance);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		instance.setVisible(true);
	}
}
