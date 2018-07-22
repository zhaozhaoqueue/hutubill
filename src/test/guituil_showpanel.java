package test;
import javax.swing.JButton;
import javax.swing.JPanel;

import util.GUIUtil;

public class guituil_showpanel {
	public static void main(String[] args) {
		JPanel p = new JPanel();
		JButton b = new JButton("test");
		p.add(b);
		GUIUtil.showPanel(p);
	}
}
