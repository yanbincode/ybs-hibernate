package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * hibernate 工具类，利用单例模式，SessionFactory是单例的
 * 
 * @author yanbin
 * 
 */
public class HibernateUtils {

	/** 指定配置文件路径 */
	private static String cfgXmlPath = "relationship/anno/hibernate.cfg.xml";

	/** 私有空构造 */
	private HibernateUtils() {
	}

	/** 私有成员变量 */
	private final static SessionFactory sessionFactory = generateSessionFactory();

	/**
	 * 私有方法生成 ,保证单例
	 * 
	 * @return
	 */
	private static SessionFactory generateSessionFactory() {
		Configuration cfg = new Configuration().configure(cfgXmlPath);
		// 利用注解，则需要另外的配置类。但是3.6之后就统一用Configuration就行了
		// Configuration cfg = new
		// AnnotationConfiguration().configure("annotation/hibernate.cfg.xml");
		return cfg.buildSessionFactory();
	}

	/**
	 * 公用方法获取SessionFactory
	 * 
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 公用方法打开session
	 * 
	 * @return
	 */
	public static Session openSession() {
		return sessionFactory.openSession();

	}

	/**
	 * 利用getCurrentSession()打开session
	 * 
	 * @return
	 */
	public static Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public static String getCfgXmlPath() {
		return cfgXmlPath;
	}

	public static void setCfgXmlPath(String cfgXmlPath) {
		HibernateUtils.cfgXmlPath = cfgXmlPath;
	}

}
