package springsecurity.security.daoimpl;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springsecurity.security.appuser.ApplicationUser;
import springsecurity.security.dao.ApplicationUserDao;

@Repository
public class ApplicationUserDaoImpl implements ApplicationUserDao {

	public ApplicationUserDaoImpl() {
	}

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// Get application user using the email
	public ApplicationUser getUser(String email) {
		Session session = this.sessionFactory.openSession();
		ApplicationUser user = session.get(ApplicationUser.class, email);
		session.close();
		return user;

	}
	
	@Transactional
   //Save the application user authorization part
	@Override
	public void saveAppUser(ApplicationUser appUser) {
				
		//Get the current session 
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(appUser);
		tx.commit();
		session.close();
	}

}
