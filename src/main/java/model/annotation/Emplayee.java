package model.annotation;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import enumtype.Title;

/**
 * 员工实体
 * 
 * @author yanbin
 * 
 */
// 标记为实体，与数据库对应
@Entity
// 如果需要用于指定表名
@Table(name = "EMPLAYEE")
public class Emplayee implements Serializable {

	private static final long serialVersionUID = 8181919056830846759L;

	// 标记为主键
	@Id
	// 主键生成策略 ：默认GenerationType 是auto同native
	// 指定sequence ，genertor = name需要相同，然后sequenceName 指定数据库的sequence
	@SequenceGenerator(name = "S_EARN_INFO", sequenceName = "S_EARN_INFO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_EARN_INFO")
	@Column(name = "RECORD_ID")
	private Long recordId;
	// 如果需要用于指定列名
	@Column(name = "FULL_NAME")
	private String fullName;
	private Long age;
	@Column(name = "ROLE_ID")
	private Long roleId;
	@Column(name = "DPT_ID")
	private Long dptId;
	// hibernate存储时间，默认存储 年月日 时分秒。@Temporal可以指定存储格式
	@Temporal(TemporalType.DATE)
	@Column(name = "ENTRY_DATE")
	private Date entryDate;

	// updatable = false 在更新的时候，如果没有变化，则不会在update语句中。如果字段很长可以提高效率
	@Column(name = "remark", updatable = false)
	private String remark;

	// 属性是枚举，用@Enumerated进行解析
	@Enumerated(EnumType.STRING)
	private Title title;

	// @Transient 标记这个字段是瞬时的，不会关联数据库
	@Transient
	private String gender;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
