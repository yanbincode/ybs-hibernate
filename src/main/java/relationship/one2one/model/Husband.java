package relationship.one2one.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Husband {

	@Id
	@SequenceGenerator(name = "s_husband", sequenceName = "s_husband")
	@GeneratedValue(generator = "s_husband", strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;

	// 配置1对1
	@OneToOne
	// 指定关联的字段
	@JoinColumn(name = "wifeId")
	private Wife wife;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Wife getWife() {
		return wife;
	}

	public void setWife(Wife wife) {
		this.wife = wife;
	}

}
