package util;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIUtil {
	private static String imageFolder = "e:/projects/hutubill/img";
	
	//�����������Ƿ�Ϊ�գ����Ϊ�գ�����false��
	public static boolean checkEmpty(JTextField tf, String input) {
		String text = tf.getText().trim();
		if(text.length()==0) {
			JOptionPane.showMessageDialog(null, input+" ����Ϊ��");
			tf.grabFocus();
			return false;
		}
		return true;
	}
	
	//�����������Ƿ�Ϊ���ָ�ʽ, ����������ָ�ʽ������false
	public static boolean checkNumber(JTextField tf, String input) {
		if(!checkEmpty(tf, input))
			return false;
		String text = tf.getText().trim();
		try {
			int i = Integer.parseInt(text);
			return true;
		}
		catch(NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, input + " ��Ҫ������");
			tf.grabFocus();
			return false;
		}
	}
	
	//���һ������������Ƿ�Ϊ0
	public static boolean checkZero(JTextField tf, String input) {
		if(!checkNumber(tf, input))
			return false;
		String text = tf.getText().trim();
		if(Integer.parseInt(text) == 0) {
			JOptionPane.showMessageDialog(null, input + " ����Ϊ��");
			tf.grabFocus();
			return false;
		}
		return true;
	}
	
	//������������ǰ��ɫ
	public static void setColor(Color color, JComponent... cs) {
		for(JComponent c: cs) {
			c.setForeground(color);
		}
	}
	
	//����ť����ͼ�꣬�����ı���������ʾ
	public static void setImageIcon(JButton b, String fileName, String tip) {
		ImageIcon i = new ImageIcon(new File(imageFolder, fileName).getAbsolutePath());
		b.setIcon(i);
		b.setToolTipText(tip);
		b.setText(tip);
		b.setPreferredSize(new Dimension(61, 81));
		b.setVerticalTextPosition(JButton.BOTTOM);
		b.setHorizontalTextPosition(JButton.CENTER);
	}
	
	//ʹ��Ƥ��
	public static void useLNF() {
		try {
			javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ������ʾһ���������
	 * @param p
	 * @param strech �������,1��ʾ����Ļ
	 */
	public static void showPanel(JPanel p, double strech) {
		JFrame f = new JFrame();
		f.setLocationRelativeTo(null);
		f.setSize(500, 500);
		CenterPanel cp = new CenterPanel(strech);
		f.setContentPane(cp);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		cp.show(p);
	}
	public static void showPanel(JPanel p) {
		showPanel(p, 0.85);
	}
}
