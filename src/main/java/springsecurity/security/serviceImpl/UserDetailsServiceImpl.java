package springsecurity.security.serviceImpl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import springsecurity.security.appuser.AppUserDetails;
import springsecurity.security.appuser.ApplicationUser;
import springsecurity.security.services.AppUserService;

public class UserDetailsServiceImpl  implements UserDetailsService{
	
  @Autowired
  private AppUserService AppuserService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		ApplicationUser usr = AppuserService.getUser(email);
		 if (usr == null) {
	            throw new UsernameNotFoundException("User not found.");
	        }

	        return new AppUserDetails(usr);
	    }
	}


