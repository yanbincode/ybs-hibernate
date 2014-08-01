package relationship.many2one.model;

import java.util.Set;

public class XmlDepartment {

	private Long dptId;
	private String name;
	private Set<XmlEmployee> employees;

	public Long getDptId() {
		return dptId;
	}

	public void setDptId(Long dptId) {
		this.dptId = dptId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<XmlEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<XmlEmployee> employees) {
		this.employees = employees;
	}

}
