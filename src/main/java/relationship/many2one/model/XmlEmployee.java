package relationship.many2one.model;

public class XmlEmployee {

	private Long empId;
	private String name;
	private XmlDepartment department;

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public XmlDepartment getDepartment() {
		return department;
	}

	public void setDepartment(XmlDepartment department) {
		this.department = department;
	}

}
