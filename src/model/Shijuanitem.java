package model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn; 
import javax.persistence.ManyToOne;
import javax.persistence.Table; 
//试卷条目
@Entity
@Table(name="t_ShijuanItem")
public class Shijuanitem {

	@Id
	@GeneratedValue
	private int id;
	
	private int deletestatus;
	
	@ManyToOne 
	@JoinColumn(name="shijuanid")
	private Shijuan shijuan;//关联的试卷
	
	@ManyToOne 
	@JoinColumn(name="shitiid")
	private Shiti shiti;//关联的试题
	
	private double fenzhi;//分值

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

	public Shijuan getShijuan() {
		return shijuan;
	}

	public void setShijuan(Shijuan shijuan) {
		this.shijuan = shijuan;
	}

	public Shiti getShiti() {
		return shiti;
	}

	public void setShiti(Shiti shiti) {
		this.shiti = shiti;
	}

	public double getFenzhi() {
		return fenzhi;
	}

	public void setFenzhi(double fenzhi) {
		this.fenzhi = fenzhi;
	}
	
	
	

	
	

	

	

	
	

	

	

	
	
	
	
}
