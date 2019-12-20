package com.wha.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wha.springmvc.dao.UserDao;
import com.wha.springmvc.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
    private UserDao dao;
 
	public List<User> findAllUsers() {
		//return users;
		return dao.findAllUsers();
	}
	
	/*private static final AtomicLong counter = new AtomicLong();
	private static List<User> users;
	static{
		users= populateDummyUsers();
	}*/

	
	public User findById(long id) {
		/*for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;*/
		return dao.findById((int)id);
	}
	
	public User findByName(String name) {
		/*for(User user : users){
			if(user.getUsername().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;*/
		return dao.findByName(name);
	}
	
	public void saveUser(User user) {
		/*user.setId(counter.incrementAndGet());
		users.add(user);*/
		dao.save(user);
	}
	
	
    /* pas besoin de faire un save car cette methode est dans la transaction  */
	public void updateUser(User user) {
		/*int index = users.indexOf(user);
		users.set(index, user);*/
		
		User entity = dao.findById((int)user.getId());
        if(entity!=null){
            entity.setUsername(user.getUsername());
            entity.setAddress(user.getAddress());
            entity.setEmail(user.getEmail());
        }
        dao.save(entity);
	}

	public void deleteUserById(long id) {
		dao.deleteUserById((int)id);
//		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
//		    User user = iterator.next();
//		    if (user.getId() == id) {
//		        iterator.remove();
//		    }
//		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername())!=null;
	}
	
	public void deleteAllUsers(){
		//users.clear();
	}

//	private static List<User> populateDummyUsers(){
//		List<User> users = new ArrayList<User>();
//		users.add(new User(counter.incrementAndGet(),"Sam", "PARIS", "sam@abc.com"));
//		users.add(new User(counter.incrementAndGet(),"wajih", "rue albert 1er COLOMBES", "wajih@formation.com"));
//		users.add(new User(counter.incrementAndGet(),"Tomy", "ALBAMA", "tomy@abc.com"));
//		return users;
//	}

}
