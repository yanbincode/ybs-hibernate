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
 * 多对多实体
 * 
 * @author yanbin
 * 
 */
@Entity
public class Student {

	@Id
	@SequenceGenerator(name = "s_student", sequenceName = "s_student")
	@GeneratedValue(generator = "s_student", strategy = GenerationType.SEQUENCE)
	private Long stuId;
	private String name;
	/**
	 * 只在一方设定多对多，则为单向多对多
	 */
	@ManyToMany
	@JoinTable(name = "teacher_student", joinColumns = @JoinColumn(name = "stu_id"), inverseJoinColumns = @JoinColumn(name = "tch_id"))
	private Set<Teacher> teachers;

	public Long getStuId() {
		return stuId;
	}

	public void setStuId(Long stuId) {
		this.stuId = stuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

}
