package composite.test;

import model.composite.Bus;
import model.composite.Car;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import utils.HibernateUtils;

import composite.id.BusPK;
import composite.id.CarPK;

public class CompositeTest {

	@Test
	public void save() {
		Car car = new Car();

		CarPK pk = new CarPK();
		pk.setModelId(11l);
		pk.setColor("red");
		car.setCarPK(pk);
		car.setWidth(100l);
		car.setHeight(200l);
		car.setEngine("t1.4");
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(car);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Test
	public void busSave() {
		Bus bus = new Bus();

		BusPK pk = new BusPK();
		pk.setModelId(10l);
		pk.setColor("red");
		// bus.setBusPK(pk);
		pk.setModelId(14l);
		pk.setColor("blue");
		bus.setModelId(131l);
		bus.setColor("blue");
		bus.setWidth(100l);
		bus.setHeight(200l);
		bus.setEngine("t1.4");
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(bus);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
