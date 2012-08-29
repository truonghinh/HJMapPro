/**
 * 
 */
package gov.vn.hcmgis.connection;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.reflectionassert.ReflectionAssert;

/**
 * @author hdk
 *
 */
public class UserDAOTest extends UnitilsJUnit4 {

	private UserDAO _userDao;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		_userDao=new UserDAO();
	}

	/**
	 * Test method for {@link gov.vn.hcmgis.connection.UserDAO#FindByName(java.lang.String)}.
	 */
	@Test
	public void testFindByName() {
		IUserInfo userInfo=_userDao.FindByName("hinh");
		ReflectionAssert.assertPropertyLenientEquals ("_userName", "hinh", userInfo);
	}

}
