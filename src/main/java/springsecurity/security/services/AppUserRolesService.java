package springsecurity.security.services;

import springsecurity.security.appuser.AppUserRole;

//User roles service
public interface AppUserRolesService {

		// Get the user role using id.
		public AppUserRole getUserRole(int roleId);

}
