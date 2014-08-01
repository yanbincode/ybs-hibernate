package composite.id;

import java.io.Serializable;

/**
 * 联合主键，定义一个联合主键类<br>
 * 1、需要实现Serializable借口<br>
 * 2、重写equals 方法，<br>
 * 3、重写hashCode方法
 * 
 * @author yanbin
 * 
 */
public class CarPK implements Serializable {

	private static final long serialVersionUID = -8343085853792029913L;

	private Long modelId;
	private String color;

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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CarPK) {
			CarPK pk = (CarPK) obj;
			if (this.modelId == pk.getModelId() && this.color.equals(pk.getColor())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.color.hashCode();
	}

}
