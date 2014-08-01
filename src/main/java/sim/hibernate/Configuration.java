package sim.hibernate;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 用于读取配置文件，并生成beanFactory
 * 
 * @author yanbin
 * 
 */
public class Configuration {

	private String driver_class;
	private String url;
	private String userName;
	private String password;

	private List<String> mappingPaths;

	public Configuration() {
	}

	public Configuration configure(String path) {
		// 解析xml
		readXml(path);
		return this;
	}

	public SessionFactory buildSessionFactory() {
		// 赋值jdbc connection
		Connection connection = openConnection();
		SessionFactory sessionFactory = new SessionFactory(connection, mappingPaths);
		return sessionFactory;
	}

	/**
	 * 读取xml
	 * 
	 * @param path
	 */
	@SuppressWarnings("unchecked")
	private void readXml(String path) {
		try {
			// 根据类路径获取配置文件
			SAXReader saxReader = new SAXReader();
			URL xmlPath = getClass().getClassLoader().getResource(path);
			Document document = saxReader.read(xmlPath);
			Element root = document.getRootElement();
			Iterator<Element> iterator = root.elementIterator("session-factory");
			if (iterator.hasNext()) {
				Element sessionElement = iterator.next();
				iterator = sessionElement.elementIterator("property");
				while (iterator.hasNext()) {
					Element element = iterator.next();
					// 获取属性值
					String name = element.attributeValue("name");
					String value = element.getTextTrim();
					setProperty(name, value);
				}
				iterator = sessionElement.elementIterator("mapping");
				while (iterator.hasNext()) {
					Element element = iterator.next();
					String value = element.attributeValue("resource");
					mappingPaths = new ArrayList<String>();
					mappingPaths.add(value);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断属性值，然后赋值
	 * 
	 * @param name
	 * @param value
	 */
	private void setProperty(String name, String value) {
		if (name.contains("driver_class")) {
			driver_class = value;
		} else if (name.contains("url")) {
			url = value;
		} else if (name.contains("username")) {
			userName = value;
		} else if (name.contains("password")) {
			password = value;
		}
	}

	/**
	 * 利用jdbc 返回 Connection
	 */
	private Connection openConnection() {
		Connection connection = null;
		try {
			Class.forName(driver_class);
			connection = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
