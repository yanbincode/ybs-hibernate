package relationship.one2one.model;

public class XmlManager {

	private Long id;
	private String name;
	private XmlManagerCard managerCard;

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

	public XmlManagerCard getManagerCard() {
		return managerCard;
	}

	public void setManagerCard(XmlManagerCard managerCard) {
		this.managerCard = managerCard;
	}

}
