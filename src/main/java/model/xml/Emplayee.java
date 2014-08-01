package model.xml;

/**
 * 员工实体
 * 
 * @author yanbin
 * 
 */
public class Emplayee {

	private Long recordId;
	private String fullName;
	private Long age;
	private Long roleId;
	private Long dptId;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getDptId() {
		return dptId;
	}

	public void setDptId(Long dptId) {
		this.dptId = dptId;
	}

}
