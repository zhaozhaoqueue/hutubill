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
		f.setTitle("一本糊涂账");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JToolBar tb = new JToolBar();
		
		JButton bsepnd = new JButton("消费一览");
		JButton brecord = new JButton("记一笔");
		JButton bcategory = new JButton("消费分类");
		JButton breport = new JButton("月消费报告");
		JButton bsetting = new JButton("设置");
		JButton bbackup = new JButton("备份");
		JButton brecover = new JButton("恢复");
		
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
