package api.other;

import model.annotation.Emplayee;

import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.HibernateUtils;

/**
 * clear 方法就是清除缓存 （一级缓存）
 * 
 * @author yanbin
 * 
 */
public class Clear {

	public void clearOper() {

		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		// 会执行一次 数据库查询语句
		Emplayee emplayee = (Emplayee) session.get(Emplayee.class, 31100l);
		System.out.println(emplayee.getFullName());

		// 这个get()方法将不再执行sql语句，因为缓存中已经存在
		Emplayee emplayee2 = (Emplayee) session.get(Emplayee.class, 31100l);
		System.out.println(emplayee2.getFullName());

		// 执行一下clear()方法则会再次发送sql语句
		session.clear();

		Emplayee emplayee3 = (Emplayee) session.get(Emplayee.class, 31100l);
		System.out.println(emplayee3.getFullName());

		tx.commit();
	}

}
