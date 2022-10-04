package springsecurity.security.dao;

import java.util.List;



import springsecurity.security.appuser.ApplicationUser;

public interface ApplicationUserDao {
	

	public  ApplicationUser getUser(String email);

	

}
