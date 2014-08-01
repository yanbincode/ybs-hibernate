package sim.hibernate;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import model.xml.Emplayee;

/**
 * 简单模拟hibernate 实现 O/R M
 * 
 * @author yanbin
 * 
 */
public class Session {

	private Connection connection;

	/** 读取hbm.xml 配置文件匹配 对象字段名 和 数据库列名 */
	private List<Map<String, String>> orms;

	private String tableName;
	private String objectName;
	private Map<String, String> orm;
	private String[] methodNames;

	public Session(Connection connection, List<Map<String, String>> orms) {
		this.connection = connection;
		this.orms = orms;
	}

	@SuppressWarnings("rawtypes")
	public void save(Emplayee emplayee) {
		// Class clazz = Class.forName(objectName);
		// Object obj = clazz.newInstance();
		// // 判断类型
		// if (obj.getClass() == object.getClass()) {
		// }
		init();
		String sql = createSql();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			for (int i = 0; i < methodNames.length; i++) {
				Method method = emplayee.getClass().getMethod(methodNames[i]);
				Class returnType = method.getReturnType();
				if (returnType.getName().equals("java.lang.String")) {
					String result = (String) method.invoke(emplayee);
					pstmt.setString(i + 1, result);
				}
				if (returnType.getName().equals("java.lang.Long")) {
					Long result = (Long) method.invoke(emplayee);
					pstmt.setLong(i + 1, result);
				}
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 初始化表名，实体名，字段映射map
	 */
	private void init() {
		if (null != orms && orms.size() > 0) {
			orm = orms.get(0);
			tableName = orm.get("table");
			objectName = orm.get("objName");
		}
	}

	private String createSql() {
		String columns = "";
		String qmarkNums = "";
		methodNames = new String[orm.size() - 2];

		int index = 0;
		for (String key : orm.keySet()) {
			if ("table".equals(key)) {
				continue;
			}
			if ("objName".equals(key)) {
				continue;
			}
			String value = orm.get(key);
			columns += key + ",";
			methodNames[index] = "get" + value.substring(0, 1).toUpperCase() + value.substring(1);
			qmarkNums += "?,";
			index++;
		}
		columns = columns.substring(0, columns.length() - 1);
		qmarkNums = qmarkNums.substring(0, qmarkNums.length() - 1);

		String sql = "insert into " + tableName + "(" + columns + ") values (" + qmarkNums + ")";
		return sql;
	}

}
