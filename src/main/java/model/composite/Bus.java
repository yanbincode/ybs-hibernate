package model.composite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 利用注解的方式配置联合主键<br>
 * 三种方式：<br>
 * 1、主键类加注解@Embeddable ， 实体类字段加 @Id <br>
 * 2、实体类字段加 @EmbeddedId <br>
 * 3、实体类保留联合主键的属性都加上 @Id ；实体类加注解 @idClass 指定主键类。<br>
 * 第三种方式也可以不要指定主键类，则需要实体类加上Serializable
 * 
 * 
 * @author yanbin
 * 
 */
@Entity
@Table(name = "bus")
// @IdClass(BusPK.class)
public class Bus implements Serializable {

	private static final long serialVersionUID = 5300506498386530564L;

	// @Id
	// @EmbeddedId
	// private BusPK busPK;
	@Id
	private Long modelId;
	@Id
	private String color;
	private Long width;
	private Long height;
	private String engine;

	// public BusPK getBusPK() {
	// return busPK;
	// }
	//
	// public void setBusPK(BusPK busPK) {
	// this.busPK = busPK;
	// }

	public Long getWidth() {
		return width;
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setWidth(Long width) {
		this.width = width;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

}
