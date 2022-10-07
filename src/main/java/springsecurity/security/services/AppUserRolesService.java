package springsecurity.security.services;

import java.util.List;


import springsecurity.security.appuser.AppUserRole;

//User roles service
public interface AppUserRolesService {

		// Get the user role using id.
		public AppUserRole getUserRole(int roleId);
		
		// Get the user role name using id.
		public List<AppUserRole> getUserRoleByName(int roleId);


}
