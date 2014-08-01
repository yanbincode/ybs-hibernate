package client;

import java.util.List;

import model.annotation.Emplayee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import utils.HibernateUtils;

/**
 * hibernate操作数据库。
 * 
 * @author yanbin
 * 
 */
public class HibernateOper {

	/**
	 * hibernate 操作数据库，基本方式：7个步骤示例
	 */
	public void oper() {
		// 1、获取配置文件
		Configuration cfg = new Configuration().configure("annotation/hibernate.cfg.xml");
		// hibernate3 注解需要AnnotationConfiguration来读取注解配置
		// hibernate4之后废弃了。可以直接用Configuration
		// Configuration cfg = new
		// AnnotationConfiguration().configure("annotation/hibernate.cfg.xml");

		// 2、构造sessionFactory
		// buildSessionFactory()这个方法在4.0之后的hibernate版本声明为过时的。但这个方法确实很方便。
		SessionFactory sessionFactory = cfg.buildSessionFactory();

		// 新的获取方式
		// ServiceRegistryBuilder serviceRegistry = new
		// ServiceRegistryBuilder();
		// 用这种方式是为了 解决4.0后xml配置
		// SessionFactory sf =
		// cfg.buildSessionFactory(serviceRegistry.applySettings(cfg.getProperties())
		// .buildServiceRegistry());

		// 3、获取session ，打开session 建议使用getCurrentSession()

		// openSession()和getCurrentSession()的区别：
		// (1)、getCurrentSession()与线程绑定，并且需要hibernate.cfg.xml中指定配置
		// (2)、getCurrentSession() 事务结束就自动关闭，不需要close()；openSession则需要close
		// (3)、getCurrentSession() 使用当前存在的session没有则新建；openSession每次都新建

		// Session session = sessionFactory.getCurrentSession();
		Session session = sessionFactory.openSession();

		Transaction transaction = null;
		try {
			// 4、开启事务
			transaction = session.beginTransaction();

			// 5、持久化操作，直接面向对象的进行操作
			String sql = "insert into emplayee values (s_emplayee.nextval, 'yanbin', 26, 1, 1)";
			session.createSQLQuery(sql).executeUpdate();

			// 6、提交事务 或者回滚事务
			transaction.commit();
		} catch (Exception e) {
			// 回滚事务
			if (null != transaction) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			// 7、关闭session 和sessionFactory
			session.close();
		}
	}

	/**
	 * 增加
	 * 
	 * @param emplayee
	 */
	public void add(Emplayee emplayee) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// save() 数据库 insert
			session.save(emplayee);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * 修改<br>
	 * 注意：修改先要获取持久态的对象
	 * 
	 * @param emplayee
	 */
	public void modify(Emplayee emplayee) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			// update() 数据库update :
			// (1)、update建议先get()获取持久态的再update ，但是持久态修改一个字段会执行update所有字段
			// (2)、如果update 瞬时态的，没有ID的对象会报错
			// (3)、set数据库中有的ID，脱管状态也可以进行update
			session.update(emplayee);

			// saveOrUpdate() 数据库 中没有则save 数据库中有就update
			session.saveOrUpdate(emplayee);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * 删除<br>
	 * 注意：删除先要获取持久态的对象
	 * 
	 * @param emplayee
	 */
	public void delete(Emplayee emplayee) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// delete() 数据库 delete
			session.delete(emplayee);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * 查询单条
	 * 
	 * @param recordId
	 * @return
	 */
	public Emplayee get(Long recordId) {
		Session session = HibernateUtils.openSession();
		// load()和get()的区别：get()调用就执行sql查询，load()则是在commit的时候执行sql查询。
		// load在session提交之后会报错，get则不会

		// load() 数据库 select 获取
		// Emplayee emplayee = (Emplayee) session.load(Emplayee.class,1l);

		// get() 数据库 select获取一条
		Emplayee emplayee = (Emplayee) session.get(Emplayee.class, recordId);
		session.close();
		return emplayee;
	}

	/**
	 * 获取所有的实体;利用hql
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Emplayee> getAll() {
		String hql = "from Emplayee";
		Session session = HibernateUtils.openSession();
		List<Emplayee> emplayees = session.createQuery(hql).list();
		session.close();
		return emplayees;
	}

	/**
	 * 利用session的createSQLQuery来获取返回值。 <br>
	 * 但是list中存放的是Ljava.lang.Object 类型。是一个Object数组。需要进行转换
	 */
	@SuppressWarnings("unchecked")
	public void getSqlAll() {
		String sql = "select * from emplayee";
		Session session = HibernateUtils.openSession();
		List<Object[]> list = session.createSQLQuery(sql).list();
		for (Object[] objs : list) {
			for (Object obj : objs) {
				System.out.print(obj);
			}
			System.out.println();
		}
		session.close();
	}

}
