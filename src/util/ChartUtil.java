package util;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;

import com.objectplanet.chart.BarChart;
import com.objectplanet.chart.Chart;

import entity.Record;

public class ChartUtil {
	
	//��ȡ�������ݵ����ֵ��Ϊ��ͼ��׼��
	public static int max(double[] sampleValues) {
		int max = 0;
		for(double v: sampleValues) {
			if(v>max)
				max = (int) v;
		}
		return max;
	}
	
	//��ȡ���ݱ�ǩ
	public static String[] sampleLabels(List<Record> rs) {
		String[] sampleLabels = new String[rs.size()];
		for(int i=0;i<sampleLabels.length; i++) {
			if(i%5 == 0)
				sampleLabels[i] = String.valueOf(i + 1 + "��");
		}
		return sampleLabels;
		
	}
	
	//��ȡ����
	public static double[] sampleValues(List<Record> rs) {
		double[] sampleValues = new double[rs.size()];
		for(int i=0;i<rs.size();i++) 
			sampleValues[i] = rs.get(i).getSpend();
		
		return sampleValues;
	}
	
	//ʹ��ʵ�����ݻ�ͼ
	public static Image getImage(List<Record> rs, int width, int height) {
		double[] sampleValues = sampleValues(rs);
		String[] sampleLabels = sampleLabels(rs);
		int max = max(sampleValues);
		
		//������ɫ
		Color[] colors = new Color[] {ColorUtil.blueColor};
		BarChart chart = new BarChart();
		
		chart.setSampleCount(sampleValues.length);
		chart.setSampleValues(0, sampleValues);
		chart.setSampleLabels(sampleLabels);
		chart.setSampleColors(colors);
		chart.setRange(0, max*1.2);
		chart.setValueLinesOn(true);
		chart.setSampleLabelsOn(true);
		chart.setSampleLabelStyle(Chart.BELOW);
		
		chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));
		chart.setLegendOn(true);
		chart.setLegendPosition(Chart.LEFT);
		chart.setLegendLabels(new String[] {"�����ѱ���"});
		chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13));
		chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));
		chart.setChartBackground(Color.WHITE);
		chart.setBackground(ColorUtil.backgroundColor);
		
		Image im = chart.getImage(width, height);
		return im;
	}
	
	
	//ģ������
	//��ȡģ�����ݱ�ǩ
	public static String[] sampleLabels() {
		String[] sampleLabels = new String[30];
		for(int i=0;i<sampleLabels.length; i++) {
			if(i%5 == 0)
				sampleLabels[i] = String.valueOf(i + 1 + "��");
		}
		return sampleLabels;
		
	}
	
	//��ȡģ������
	public static double[] sampleValues() {
		double[] result = new double[30];
		for(int i=0;i<result.length;i++) {
			result[i] = (int)(Math.random()*300);
		}
		return result;
	}
	
	//��һ��bar chart,�������chart��image
	public static Image getImage(int width, int height) {
		double[] sampleValues = sampleValues();
		String[] sampleLabels = sampleLabels();
		int max = max(sampleValues);
		
		//������ɫ
		Color[] colors = new Color[] {ColorUtil.blueColor};
		BarChart chart = new BarChart();
		
		chart.setSampleCount(sampleValues.length);
		chart.setSampleValues(0, sampleValues);
		chart.setSampleLabels(sampleLabels);
		chart.setSampleColors(colors);
		chart.setRange(0, max*1.2);
		chart.setValueLinesOn(true);
		chart.setSampleLabelsOn(true);
		chart.setSampleLabelStyle(Chart.BELOW);
		
		chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));
		chart.setLegendOn(true);
		chart.setLegendPosition(Chart.LEFT);
		chart.setLegendLabels(new String[] {"�����ѱ���"});
		chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13));
		chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));
		chart.setChartBackground(Color.WHITE);
		chart.setBackground(ColorUtil.backgroundColor);
		
		Image im = chart.getImage(width, height);
		return im;
	}
	
	public static void main(String[] args) {
		GUIUtil.useLNF();
		JPanel p = new JPanel();
		JLabel l = new JLabel();
		Image img = ChartUtil.getImage(400, 300);
		Icon i = new ImageIcon(img);
		l.setIcon(i);
		p.add(l);
		GUIUtil.showPanel(p);
	}
}
