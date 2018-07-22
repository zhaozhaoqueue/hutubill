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
	
	//检测组件内容是否为空，如果为空，返回false；
	public static boolean checkEmpty(JTextField tf, String input) {
		String text = tf.getText().trim();
		if(text.length()==0) {
			JOptionPane.showMessageDialog(null, input+" 不能为空");
			tf.grabFocus();
			return false;
		}
		return true;
	}
	
	//检查组件内容是否为数字格式, 如果不是数字格式，返回false
	public static boolean checkNumber(JTextField tf, String input) {
		if(!checkEmpty(tf, input))
			return false;
		String text = tf.getText().trim();
		try {
			int i = Integer.parseInt(text);
			return true;
		}
		catch(NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, input + " 需要是整数");
			tf.grabFocus();
			return false;
		}
	}
	
	//检测一个组件的内容是否为0
	public static boolean checkZero(JTextField tf, String input) {
		if(!checkNumber(tf, input))
			return false;
		String text = tf.getText().trim();
		if(Integer.parseInt(text) == 0) {
			JOptionPane.showMessageDialog(null, input + " 不能为零");
			tf.grabFocus();
			return false;
		}
		return true;
	}
	
	//给多个组件设置前景色
	public static void setColor(Color color, JComponent... cs) {
		for(JComponent c: cs) {
			c.setForeground(color);
		}
	}
	
	//给按钮设置图标，增加文本及文字提示
	public static void setImageIcon(JButton b, String fileName, String tip) {
		ImageIcon i = new ImageIcon(new File(imageFolder, fileName).getAbsolutePath());
		b.setIcon(i);
		b.setToolTipText(tip);
		b.setText(tip);
		b.setPreferredSize(new Dimension(61, 81));
		b.setVerticalTextPosition(JButton.BOTTOM);
		b.setHorizontalTextPosition(JButton.CENTER);
	}
	
	//使用皮肤
	public static void useLNF() {
		try {
			javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 快速显示一个面板内容
	 * @param p
	 * @param strech 拉伸比例,1表示满屏幕
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
