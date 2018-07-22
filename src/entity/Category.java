package entity;

public class Category {
	public int id;
	public String name;
	
	public int recordNumber;
	
	public int getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//在JComboBox中显示时会调用此方法，显示分类名称
	public String toString() {
		return name;
	}
}
