import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class HutuMainFrame {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(500, 450);
		f.setLocationRelativeTo(null);
		f.setTitle("һ����Ϳ��");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JToolBar tb = new JToolBar();
		
		JButton bsepnd = new JButton("����һ��");
		JButton brecord = new JButton("��һ��");
		JButton bcategory = new JButton("���ѷ���");
		JButton breport = new JButton("�����ѱ���");
		JButton bsetting = new JButton("����");
		JButton bbackup = new JButton("����");
		JButton brecover = new JButton("�ָ�");
		
		tb.add(bsepnd);
		tb.add(brecord);
		tb.add(bcategory);
		tb.add(breport);
		tb.add(bsetting);
		tb.add(bbackup);
		tb.add(brecover);
		
		f.setLayout(new BorderLayout());
		f.add(tb, BorderLayout.NORTH);
		f.add(new JPanel(), BorderLayout.CENTER);
		
		f.setVisible(true);
		
		bsepnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		brecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		bcategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		breport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		bsetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		bbackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		brecover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
