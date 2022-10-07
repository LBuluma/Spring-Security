package springsecurity.security.dao;

import springsecurity.security.appuser.ApplicationUser;

public interface ApplicationUserDao {
	

	public  ApplicationUser getUser(String email);

	public  void saveAppUser(ApplicationUser appUser);

}
