package model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//课程 
@Entity
@Table(name="t_Kecheng")
public class Kecheng {

	@Id
	@GeneratedValue
	private int id;//主键
	
	private int deletestatus;//是否删除的标志，0表示正常，1表示删除
	
	private String mingchen;//课程名称

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(int deletestatus) {
		this.deletestatus = deletestatus;
	}

	public String getMingchen() {
		return mingchen;
	}

	public void setMingchen(String mingchen) {
		this.mingchen = mingchen;
	}
	
	
	
	
}
