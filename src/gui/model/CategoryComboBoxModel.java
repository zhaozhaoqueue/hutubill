package gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import entity.Category;
import service.CategoryService;

public class CategoryComboBoxModel implements ComboBoxModel<Category>{
	//private List<String> cs = new ArrayList<>();
	//String c ;
	public List<Category> cs = new CategoryService().list();
	public Category c;
	public CategoryComboBoxModel() {
		//cs.add("����");
		//cs.add("��ͨ");
		//cs.add("ס��");
		//cs.add("����");
		//this.c = cs.get(0);
		//if(!cs.isEmpty())
			//c = cs.get(0);
	}
	
	public int getSize() {
		return cs.size();
	}
	
	public Category getElementAt(int index) {
		return cs.get(index);
	}
	
	public void setSelectedItem(Object anItem) {
		// TODO Auto-generated method stub
		c = (Category) anItem;
	}
	
	public Object getSelectedItem() {
		if(!cs.isEmpty())
			return c;
		else
			return null;
	}
	
	public void addListDataListener(ListDataListener l) {
		//δ���д
	}
	
	public void removeListDataListener(ListDataListener l) {
		//δ���д
	}
}
