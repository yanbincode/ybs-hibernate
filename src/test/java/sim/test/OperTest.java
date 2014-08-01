package sim.test;

import model.xml.Emplayee;

import org.junit.Test;

import sim.client.Oper;

public class OperTest {

	private Emplayee createEmplayee() {
		Emplayee emplayee = new Emplayee();
		emplayee.setRecordId(1000l);
		emplayee.setFullName("jibin");
		emplayee.setDptId(100l);
		emplayee.setRoleId(10l);
		emplayee.setAge(1000l);
		return emplayee;
	}

	@Test
	public void oper() {
		Oper oper = new Oper();
		oper.add(createEmplayee());
	}

}
