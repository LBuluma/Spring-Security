package springsecurity.security.services;
import springsecurity.security.appuser.ApplicationUser;


//Application user service
public interface AppUserService {
	
	//Get application user using the user email.
	public  ApplicationUser getUser(String email);

}
