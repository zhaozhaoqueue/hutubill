package util;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.panel.WorkingPanel;

public class CenterPanel extends JPanel{
	private double rate;
	private JComponent c;
	private boolean strech;
	
	public CenterPanel(double rate, boolean strech) {
		this.rate = rate;
		this.setLayout(null);
		this.strech = strech;
	}
	
	public CenterPanel(double rate) {
		this(rate, true);
	}
	
	public void repaint() {
		if(c!=null) {
			Dimension containerSize = this.getSize();
			Dimension componentSize = c.getPreferredSize();
			if(strech) {
				c.setSize((int)(containerSize.width*rate), (int)(containerSize.height*rate));
			}
			else {
				c.setSize(componentSize);
			}
			c.setLocation(containerSize.width/2 - c.getSize().width/2, containerSize.height/2 - c.getSize().height/2);
		}
		super.repaint();
	}
	
	public void show(JComponent p) {
		this.c = p;
		Component[] cs = this.getComponents();
		for(Component c: cs) {
			this.remove(c);
		}
		add(p);
		
		if(p instanceof WorkingPanel) {
			((WorkingPanel) p).updateData();
		}
		this.updateUI();
	}
	
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setSize(200,200);
		jf.setLocationRelativeTo(null);
		CenterPanel cp = new CenterPanel(0.85);
		jf.setContentPane(cp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);	
		JButton jb = new JButton("test");
		cp.show(jb);
	}
}
