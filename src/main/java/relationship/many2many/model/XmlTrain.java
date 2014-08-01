package relationship.many2many.model;

import java.util.Set;

public class XmlTrain {

	private Long id;
	private String name;
	private Set<XmlStation> stations;

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

	public Set<XmlStation> getStations() {
		return stations;
	}

	public void setStations(Set<XmlStation> stations) {
		this.stations = stations;
	}

}
