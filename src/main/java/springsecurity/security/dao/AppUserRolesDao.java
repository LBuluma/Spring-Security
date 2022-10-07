package springsecurity.security.dao;
import java.util.List;

import springsecurity.security.appuser.AppUserRole;



public interface AppUserRolesDao {

			// Get the user role using id.
			public AppUserRole getUserRole(int roleId);
			
			// Get the user role name  using id.
			public List<AppUserRole> getUserRoleByName(int roleId);
}
