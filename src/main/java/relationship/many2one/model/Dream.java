package relationship.many2one.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * 多方：一个人有多个梦想
 * 
 * @author yanbin
 * 
 */
@Entity
public class Dream {

	@Id
	@SequenceGenerator(name = "s_dream", sequenceName = "s_dream")
	@GeneratedValue(generator = "s_dream", strategy = GenerationType.SEQUENCE)
	private Long dreamId;
	private String dreamName;
	/** 多对一：只设定这边，则是多对一单向关联。如果两边都设定为双向 */
	// cascade属性：用于级联；ALL为任何时候都级联，PERSIST为增加时级联，REMOVE则为删除时
	// fetch属性：用于级联查询。 值EAGER为关联查询结果，LAZY为单查询，需要时再做查询出来。多方默认是EAGER
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	/** 这边指定的列是单方的列*/
	@JoinColumn(name = "personId")
	private Person person;

	public Long getDreamId() {
		return dreamId;
	}

	public void setDreamId(Long dreamId) {
		this.dreamId = dreamId;
	}

	public String getDreamName() {
		return dreamName;
	}

	public void setDreamName(String dreamName) {
		this.dreamName = dreamName;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
