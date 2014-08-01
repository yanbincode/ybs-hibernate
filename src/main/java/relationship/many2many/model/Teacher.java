package relationship.many2many.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

/**
 * 多对多：多对多会产生一个中间表。存放两边的主键ID做外键关联
 * 
 * @author yanbin
 * 
 */
@Entity
public class Teacher {

	@Id
	@SequenceGenerator(name = "s_teacher", sequenceName = "s_teacher")
	@GeneratedValue(generator = "s_teacher", strategy = GenerationType.SEQUENCE)
	private Long tchId;
	private String name;
	/**
	 * 如果多的只设定一方，则为单项多对多。
	 */
	@ManyToMany
	@JoinTable(name = "teacher_student", joinColumns = @JoinColumn(name = "tch_id"), inverseJoinColumns = @JoinColumn(name = "stu_id"))
	private Set<Student> students;

	public Long getTchId() {
		return tchId;
	}

	public void setTchId(Long tchId) {
		this.tchId = tchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
