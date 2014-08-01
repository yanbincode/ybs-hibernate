package relationship.many2one.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * 多对一：一方，多个梦想只对应一个人
 * 
 * @author yanbin
 * 
 */
@Entity
public class Person {

	@Id
	@SequenceGenerator(name = "s_person", sequenceName = "s_person")
	@GeneratedValue(generator = "s_person", strategy = GenerationType.SEQUENCE)
	private Long personId;
	private String name;
	/** 一对多的设定，如果只设定此方则为一方单项关联 。如果两边都设定为双向 */
	// 不指定mappedBy会出现两边都建立外键关联。mappedBy的值是被拥有方指定拥有方的属性
	// mappedBy:一定是定义在the owned side(被拥有方的)，他指向the owning side(拥有方)
	// mappedBy的含义，应该理解为，拥有方能够自动维护跟被拥有方的关系；当然，如果从被拥有方，通过手工强行来维护拥有方的关系也是可以做到的。
	// mappedBy跟JoinColumn/JoinTable总是处于互斥，JoinColumn/JoinTable总是无效的
	// 单方的fetch默认是lazy
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "person", fetch = FetchType.EAGER)
	/** 这边指定的列，是多方表中的列，一般指向多方的主键*/
	// @JoinColumn(name = "dreamId")
	private Set<Dream> dreams;

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Dream> getDreams() {
		return dreams;
	}

	public void setDreams(Set<Dream> dreams) {
		this.dreams = dreams;
	}

}
