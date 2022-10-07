package springsecurity.security.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springsecurity.security.appuser.AppUserRole;
import springsecurity.security.dao.AppUserRolesDao;

@Repository
public class AppUserRolesDaoImpl implements AppUserRolesDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public AppUserRole getUserRole(int roleId) {
		Session session = this.sessionFactory.openSession();
		AppUserRole userRoles = session.get(AppUserRole.class, roleId);
		session.close();
		return userRoles;
	}

	@Override
	public List<AppUserRole> getUserRoleByName(int roleId) {
		Session session = this.sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<AppUserRole> rolesList = session.createQuery("from AppUserRoles u where u.roleId =: roleId")
				.setParameter("roleId", roleId).getResultList();
		session.close();
		return rolesList;
	}

}
