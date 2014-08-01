package base.test;

import java.util.Date;

import model.annotation.Emplayee;

import org.junit.Test;

import client.HibernateOper;
import enumtype.Title;

public class BaseTest {

	private Emplayee createEmplayee() {
		Emplayee emplayee = new Emplayee();
		emplayee.setRecordId(1000l);
		emplayee.setFullName("jibin");
		emplayee.setDptId(100l);
		emplayee.setRoleId(10l);
		emplayee.setAge(1000l);
		emplayee.setEntryDate(new Date());
		emplayee.setTitle(Title.B);
		return emplayee;
	}

	@Test
	public void oper() {
		HibernateOper oper = new HibernateOper();
		oper.oper();
	}

	@Test
	public void add() {
		HibernateOper oper = new HibernateOper();
		oper.add(createEmplayee());
	}

	@Test
	public void modify() {
		HibernateOper oper = new HibernateOper();
		Emplayee dbEmplayee = oper.get(createEmplayee().getRecordId());
		dbEmplayee.setAge(500l);
		oper.modify(dbEmplayee);
	}

	@Test
	public void delete() {
		HibernateOper oper = new HibernateOper();
		Emplayee dbEmplayee = oper.get(2l);
		oper.delete(dbEmplayee);
	}

	@Test
	public void getAll() {
		HibernateOper oper = new HibernateOper();
		oper.getAll();
	}

	@Test
	public void getSqlAll() {
		HibernateOper oper = new HibernateOper();
		oper.getSqlAll();
	}

}
