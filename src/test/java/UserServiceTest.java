import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wha.springmvc.configuration.HelloWorldConfiguration;
import com.wha.springmvc.configuration.JpaConfiguration;
import com.wha.springmvc.dao.UserDao;
import com.wha.springmvc.model.User;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={HelloWorldConfiguration.class, JpaConfiguration.class})
public class UserServiceTest   {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void addUserTest(){
		User utilisateur = new User();
		utilisateur.setUsername("tom");
		utilisateur.setAddress("24 rue leclerc");
		utilisateur.setEmail("tom@gmail.com");
		userDao.save(utilisateur);
		List<User> utilisateurs = userDao.findAllUsers();
		assertEquals("tom", utilisateurs.get(0).getUsername());
		
	}

}
