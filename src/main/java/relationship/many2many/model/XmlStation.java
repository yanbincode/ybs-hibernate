package relationship.many2many.model;

import java.util.Set;

public class XmlStation {

	private Long id;
	private String name;
	private Set<XmlTrain> trains;

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

	public Set<XmlTrain> getTrains() {
		return trains;
	}

	public void setTrains(Set<XmlTrain> trains) {
		this.trains = trains;
	}

}
