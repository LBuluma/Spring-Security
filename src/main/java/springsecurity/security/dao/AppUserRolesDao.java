package springsecurity.security.dao;


import springsecurity.security.appuser.AppUserRole;



public interface AppUserRolesDao {

			// Get the user role using id.
			public AppUserRole getUserRole(int roleId);
}
