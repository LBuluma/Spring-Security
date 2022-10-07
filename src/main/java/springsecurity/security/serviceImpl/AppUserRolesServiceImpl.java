package springsecurity.security.serviceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springsecurity.security.appuser.AppUserRole;
import springsecurity.security.dao.AppUserRolesDao;
import springsecurity.security.services.AppUserRolesService;

//Service implementation to get user roles
@Service
public class AppUserRolesServiceImpl implements AppUserRolesService{

	@Autowired
	private AppUserRolesDao userRolesDao;
	
  //get user role using role ID
	@Override
	public AppUserRole getUserRole(int roleId) {
		return userRolesDao.getUserRole(roleId);
	}

	@Override
	public List<AppUserRole> getUserRoleByName(int roleId) {
		return userRolesDao.getUserRoleByName(roleId);

	}

	

}
