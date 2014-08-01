package sim.hibernate;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SessionFactory {

	private Connection connection;
	private List<String> mappingPaths;
	private Session session;
	private List<Map<String, String>> orms;

	public SessionFactory(Connection connection, List<String> mappingPaths) {
		this.connection = connection;
		this.mappingPaths = mappingPaths;
	}

	public Session openSession() {
		readXml();
		session = new Session(connection, orms);
		return session;
	}

	private void readXml() {
		orms = new ArrayList<Map<String, String>>();
		for (String path : mappingPaths) {
			readXml(path);
		}
	}

	@SuppressWarnings("unchecked")
	private void readXml(String path) {
		Map<String, String> orm = new HashMap<String, String>();
		try {
			// 根据类路径获取配置文件
			SAXReader saxReader = new SAXReader();
			URL xmlPath = getClass().getClassLoader().getResource(path);
			Document document = saxReader.read(xmlPath);
			Element root = document.getRootElement();

			// 获取所有指定节点
			Iterator<Element> iterator = root.elementIterator("class");
			if (iterator.hasNext()) {
				Element classElement = iterator.next();
				// 获取属性值
				String value = classElement.attributeValue("table");
				String objName = classElement.attributeValue("name");
				orm.put("table", value);
				orm.put("objName", objName);

				iterator = classElement.elementIterator("id");
				if (iterator.hasNext()) {
					Element element = iterator.next();
					String name = element.attributeValue("name");
					iterator = element.elementIterator("column");
					String column = "";
					if (iterator.hasNext()) {
						Element subElement = iterator.next();
						column = subElement.attributeValue("name");
					}
					orm.put(column, name);
				}

				iterator = classElement.elementIterator("property");
				while (iterator.hasNext()) {
					Element element = iterator.next();
					String name = element.attributeValue("name");
					Iterator<Element> columnIter = element.elementIterator("column");
					String column = "";
					if (columnIter.hasNext()) {
						Element subElement = columnIter.next();
						column = subElement.attributeValue("name");
					}
					orm.put(column, name);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		orms.add(orm);
	}

}
