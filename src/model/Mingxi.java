package model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne; 
import javax.persistence.Table;
//考试明细
@Entity
@Table(name="t_Mingxi")
public class Mingxi {


	@Id
	@GeneratedValue
	private int id;//主键
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;//考试的用户，外键
	
	@ManyToOne
	@JoinColumn(name="examid")
	private Exam exam;//关联的考试，外键
	
	private String wodedaan;//我的答案
	
	private String createtime;//添加时间
	
	
	@ManyToOne
	@JoinColumn(name="shijuanitemid")
	private Shijuanitem shijuanitem;//关联的试卷条目，外键
	
	
	private double defen;//得分
	
	private String dianping;//点评
	
	private String shifougeifen;//是否给分 未给分，已给分

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

	public String getWodedaan() {
		return wodedaan;
	}

	public void setWodedaan(String wodedaan) {
		this.wodedaan = wodedaan;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Shijuanitem getShijuanitem() {
		return shijuanitem;
	}

	public void setShijuanitem(Shijuanitem shijuanitem) {
		this.shijuanitem = shijuanitem;
	}

	public double getDefen() {
		return defen;
	}

	public void setDefen(double defen) {
		this.defen = defen;
	}

	public String getDianping() {
		return dianping;
	}

	public void setDianping(String dianping) {
		this.dianping = dianping;
	}

	public String getShifougeifen() {
		return shifougeifen;
	}

	public void setShifougeifen(String shifougeifen) {
		this.shifougeifen = shifougeifen;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	
	
	
	
	
	
	
	
	
}
