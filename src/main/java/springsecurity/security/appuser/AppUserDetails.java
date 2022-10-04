package springsecurity.security.appuser;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ApplicationUser appUser;
	
	

	public AppUserDetails(ApplicationUser appUser) {
		super();
		this.appUser = appUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return appUser.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return appUser.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		if(appUser.getLocked() == "N") {
			return false;
		}
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
//		if(appUser.getEnabled() == "N") {
//			return false;
//		}
		return true;
	}

	@Override
	public boolean isEnabled() {
		if(appUser.getEnabled() == "N") {
			return false;
		}
		return true;
	}
	
	 public ApplicationUser getUserDetails() {
	        return appUser;
	    }

}
