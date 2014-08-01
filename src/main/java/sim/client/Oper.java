package sim.client;

import model.xml.Emplayee;
import sim.hibernate.Configuration;
import sim.hibernate.Session;
import sim.hibernate.SessionFactory;

public class Oper {

	public void add(Emplayee emplayee) {
		Configuration cfg = new Configuration();
		cfg.configure("xml/hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.save(emplayee);
	}

}
