package relationship.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import relationship.many2one.model.Dream;
import relationship.many2one.model.Person;

public class RelationshipTest {

	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void beforeClass() {
		Configuration cfg = new Configuration().configure("relationship/anno/hibernate.cfg.xml");
		sessionFactory = cfg.buildSessionFactory();
	}

	@AfterClass
	public static void afterClass() {
		sessionFactory.close();
	}

	@Test
	public void testSaveDream() {
		Dream dream = new Dream();
		dream.setDreamName("挣大钱");

		Person person = new Person();
		person.setName("yanbin");

		dream.setPerson(person);

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// session.save(person);
		// 级联插入
		session.save(dream);

		session.getTransaction().commit();
	}

	@Test
	public void testSavePerson() {
		Person person = new Person();
		person.setName("p1");

		Dream d1 = new Dream();
		d1.setDreamName("d1");
		Dream d2 = new Dream();
		d2.setDreamName("d2");

		// 必须指定 person ，不入一方好用
		d1.setPerson(person);
		d2.setPerson(person);

		Set<Dream> dreams = new HashSet<Dream>();
		dreams.add(d1);
		dreams.add(d2);

		person.setDreams(dreams);

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// 级联插入
		session.save(person);

		session.getTransaction().commit();
	}

	@Test
	public void getPerson() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Person person = (Person) session.get(Person.class, 1200L);

		System.out.println(person.getName());
		for (Dream dream : person.getDreams()) {
			System.out.println(dream.getDreamId());
		}
	}

	@Test
	public void getDream() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Dream dream = (Dream) session.get(Dream.class, 1100L);
		Person person = dream.getPerson();
		System.out.println(dream.getDreamName());
		System.out.println(person.getName());

	}

}
