package model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//试卷
@Entity
@Table(name="t_Shijuan")
public class Shijuan {


	@Id
	@GeneratedValue
	private int id;
	
	private int deletestatus;
	
	private String juanming;//卷名
	
	@ManyToOne
	@JoinColumn(name="kechengid")
	private Kecheng kecheng;//课程
	
	private double zongfen;//总分
	
	private String ctime;//添加时间
	
	private String examtime;//考试时间
	
	private String nandu;//难度
	
	private String zhuangtai;//状态

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

	public String getJuanming() {
		return juanming;
	}

	public void setJuanming(String juanming) {
		this.juanming = juanming;
	}

	public Kecheng getKecheng() {
		return kecheng;
	}

	public void setKecheng(Kecheng kecheng) {
		this.kecheng = kecheng;
	}

	public double getZongfen() {
		return zongfen;
	}

	public void setZongfen(double zongfen) {
		this.zongfen = zongfen;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getExamtime() {
		return examtime;
	}

	public void setExamtime(String examtime) {
		this.examtime = examtime;
	}

	public String getNandu() {
		return nandu;
	}

	public void setNandu(String nandu) {
		this.nandu = nandu;
	}

	public String getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}


	
	
	

	


	

	
	
	
	
}
