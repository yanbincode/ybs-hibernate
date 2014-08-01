package composite.id;

import java.io.Serializable;

//@Embeddable
public class BusPK implements Serializable {

	private static final long serialVersionUID = -3881932207166976869L;

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
		if (obj instanceof BusPK) {
			BusPK pk = (BusPK) obj;
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
