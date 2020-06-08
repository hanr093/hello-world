package model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//我的考试 
@Entity
@Table(name="t_Exam")
public class Exam {

	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;//考试的学生
	
	private String bianhao;//考试编号
	
	private String createtime;//考试时间
	
	private String chengji;//考试成绩
	
	private double chengji2;//成绩2
	
	@ManyToOne
	@JoinColumn(name="shijuanid")
	private Shijuan shijuan;//关联的试卷，外键
	
	private String zhuangtai;//未阅卷  ，已阅卷
	
	@ManyToOne
	@JoinColumn(name="yuejuanrenid")
	private User yuejuanren;//阅卷人

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBianhao() {
		return bianhao;
	}

	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getChengji() {
		return chengji;
	}

	public void setChengji(String chengji) {
		this.chengji = chengji;
	}

	public double getChengji2() {
		return chengji2;
	}

	public void setChengji2(double chengji2) {
		this.chengji2 = chengji2;
	}

	public Shijuan getShijuan() {
		return shijuan;
	}

	public void setShijuan(Shijuan shijuan) {
		this.shijuan = shijuan;
	}

	public String getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}

	public User getYuejuanren() {
		return yuejuanren;
	}

	public void setYuejuanren(User yuejuanren) {
		this.yuejuanren = yuejuanren;
	}

	
	
	

	
	
	
	
	
}
