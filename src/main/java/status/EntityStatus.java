package status;

/**
 * 实体类在hibernante管理下的三种状态<br>
 * Transient , Persistent , Detached
 * 
 * @author yanbin
 * 
 */
public class EntityStatus {

	/**
	 * 三种状态的区分： 1、有没有ID 2、ID在内存中是否存在 3、ID在数据库中是否存在
	 * 
	 * Transient(瞬时态) ：有对象，没有ID，缓存中没有。 <br>
	 * Persistent(持久态) ：有ID，缓存中有，数据库有。 <br>
	 * Detached(脱管态) ：内存中有，缓存中没有，数据库中有。
	 * 
	 * 
	 * 三种状态的转换：见图
	 * 
	 */

}
