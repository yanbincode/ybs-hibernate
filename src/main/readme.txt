1、orm
2、封装jdbc
3、使用：
	3个准备, 7个步骤
	
	准备：
		第一、导入hibernate需要的jar包
		第二、hibernate.cfg.xml环境配置文件
		第三、添加实体类，以及映射文件User.hbm.xml文件（利用注解，可以不需要此配置）。
	
	步骤：见具体的dao实现类 PersonDao
	
	
hibernate的flush机制：
 
1、利用sql语句， session.createSQLQuery(sql).executeUpdate();进行插入，输出台打印出sql插入语句； 再利用sql语句，进行session.createSQLQuery(sql).uniqueResult(); 也会打印SQL查询语句，没有问题，可以查询到数据。
 
2、利用hibernate封装操作， session.save(entity); 进行插入，输出台并没有打印出插入的SQL语句， 再利用 session.get(entity,id);方法做查询 ；也没有打印出SQL查询语句，但是是可以查询到数据的。到执行事务提交语句时，插入的SQL语句被打印出来
 
3、利用hibernate的session.save(entity); 进行插入，再利用《HQL》语句进行查询，效果同上面第二点。
 
4、利用hibernate的session.save(entity); 进行插入，输出台并没有打印出插入的SQL语句。 再利用sql语句，进行session.createSQLQuery(sql).uniqueResult(); 会打印SQL查询语句。问题出现了，查询不到任何数据。这种情况下利用session.flush()方法，在查询之前执行到flush()方法，输出台会打印出插入的SQL语句。 再进行查询就有数据。

一个基本的hibernate save方法的操作流程：
 
1． 判断所要保存的实例是否已处于持久化状态，如果不是，则将其置入缓存；
 
2． 根据所要保存的实例计划一条insert sql语句，注意只是计划，并不执行；
 
3． 事务提交时执行之前所计划的insert语句；	

将tx.commit()换成session.flush，此时控制台打印出了insert语句，但是数据库中并没有添加新的记录；

flush方法的主要作用就是清理缓存，强制数据库与Hibernate缓存同步，以保证数据的一致性。它的主要动作就是向数据库发送一系列的sql语句，并执行这些sql语句，但是不会向数据库提交。而commit方法则会首先调用flush方法，然后
 
提交事务。这就是为什么我们仅仅调用flush的时候记录并未插入到数据库中的原因，因为只有提交了事务，对数据库所做的更新才会被保存下来。因为commit方法隐式的调用了flush，所以一般我们都不会显示的调用flush方法。
 
这是 hibernate的flush机制。在一些复杂的对象更新和保存的过程中就要考虑数据库操作顺序的改变以及延时flush是否对程序的结果有影响。如果确实存在着影响，那就可以在需要保持这种操作顺序的位置加入flush强制Hibernate将缓存
 
中记录的操作flush入数据库，这样看起来也许不太美观，但很有效。

jpa ： 是一个标准：比如javax.persistence.Entity; 就是jpa的注解。而 hibernate.annotation.Entity则是他的实现