package model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//试题
@Entity 
@Table(name="t_Shiti")
public class Shiti {

	@Id
	@GeneratedValue
	private int id;//主键
	
	private int deletestatus;//表示是否删除的标志，1表示删除，0表示正常
	
	private String leixing;//试题类型  选择题 判断题  填空题  问答题
	
	private String nandu;//难度

	@Column(name="wenti", columnDefinition="TEXT")
	private String wenti;//问题
	
	@Column(name="daan", columnDefinition="TEXT")
	private String daan;//答案

	private String a;//A选项
	
	private String b;//B选项
	
	private String c;//C选项
	
	private String d;//D选项
	
	@ManyToOne
	@JoinColumn(name="kechengid")
	private Kecheng kecheng;//关联的课程，外键

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

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	public String getNandu() {
		return nandu;
	}

	public void setNandu(String nandu) {
		this.nandu = nandu;
	}

	public String getWenti() {
		return wenti;
	}

	public void setWenti(String wenti) {
		this.wenti = wenti;
	}

	public String getDaan() {
		return daan;
	}

	public void setDaan(String daan) {
		this.daan = daan;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public Kecheng getKecheng() {
		return kecheng;
	}

	public void setKecheng(Kecheng kecheng) {
		this.kecheng = kecheng;
	}
	
	
	
	

	
	
	
	
}
