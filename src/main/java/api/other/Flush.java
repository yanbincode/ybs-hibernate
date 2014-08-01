package api.other;

import java.util.List;

import model.annotation.Emplayee;

import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.HibernateUtils;

/**
 * hibernate flush机制的使用
 * 
 * @author yanbin
 * 
 */
public class Flush {

	private Emplayee getEmplayee() {
		Emplayee emplayee = new Emplayee();
		emplayee.setRecordId(1000l);
		emplayee.setFullName("flush");
		emplayee.setDptId(100l);
		emplayee.setRoleId(10l);
		emplayee.setAge(1000l);
		return emplayee;
	}

	/**
	 * 两个语句都是用SQL来执行。
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public void bothSqlOper() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();

		// 利用SQL语句插入
		String insertSql = "insert into emplayee values (s_emplayee.nextval, 'yanbin', 26, 1, 1)";
		// 执行，控制台会输出insert的SQL语句
		session.createSQLQuery(insertSql).executeUpdate();
		// 利用SQL语句查询
		String selectSql = "select * from emplayee ";
		// 执行，控制台会输出select的SQL语句
		// 并且这个list里面包含了当前insert进行去的数据。但是在数据库中并没有提交进去，数据库是没有这条数据的。
		List<Object[]> list = session.createSQLQuery(selectSql).list();
		// 数据库产生数据
		tx.commit();
		session.close();
	}

	/**
	 * 两个操作都用Hibernage封装的完成
	 */
	public void bothHibOper() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		Emplayee emplayee = getEmplayee();

		// 利用Hibernate封装的save()方法保存。控制台没有输出insert语句。
		session.save(emplayee);
		// 再次利用Hibernate封装的get()方法获取。控制台也没有输出select语句。
		// 并且这个object对象中是有刚save的对象，有数据的。但是在数据库中并没有提交进去，数据库是没有这条数据的。
		@SuppressWarnings("unused")
		Object obj = session.get(Emplayee.class, emplayee.getRecordId());
		// 控制台将insert语句输出到控制台上。数据库产生数据 。整个过程中,没有出现select语句
		tx.commit();
		session.close();
	}

	/**
	 * 保存利用save()，查询利用hql。效果同bothHibOper方法
	 */
	public void saveAndHqlOper() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		Emplayee emplayee = getEmplayee();

		session.save(emplayee);
		String hql = "from Emplayee e where e.recordId = " + emplayee.getRecordId();
		// 同样是有值的，并且输出了insert和select语句。
		@SuppressWarnings("unused")
		Object obj = session.createQuery(hql).uniqueResult();
		// 数据库产生数据
		tx.commit();
		session.close();
	}

	/**
	 * 保存利用save()，查询利用sql。问题就出现了，也是flush()上场
	 */
	public void saveAndSqlOper() {
		Session session = HibernateUtils.openSession();

		// 设定 flush在何时同步数据库和缓存
		// session.setFlushMode(FlushMode.ALWAYS);

		Transaction tx = session.beginTransaction();
		Emplayee emplayee = getEmplayee();

		// 利用Hibernate封装的save()方法保存。控制台没有输出insert语句。
		session.save(emplayee);

		/** 在以下这种情况下则需要利用flush()方法，清理缓存，强制数据库与Hibernate缓存同步，以保证数据的一致性 */
		/** 执行，控制台会输出Insert语句，但是数据库中也没有提交进去，数据库是没有这条数据的。 */
		/** 执行之后，下面利用SQL查询就能查处数据 */
		session.flush();

		// 利用SQL语句查询
		String sql = "select * from emplay e where e.record_id = " + emplayee.getRecordId();
		// 执行，控制台会输出select的SQL语句
		// 这个时候的obj为空的，没有获取到值。数据库中也没有提交进去，数据库是没有这条数据的。
		@SuppressWarnings("unused")
		Object obj = session.createSQLQuery(sql).uniqueResult();
		// 数据库中产生数据
		tx.commit();
		session.close();
	}

}
