package springsecurity.security.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springsecurity.security.appuser.ApplicationUser;
import springsecurity.security.dao.ApplicationUserDao;
import springsecurity.security.services.AppUserRolesService;
import springsecurity.security.services.AppUserService;



@Service
public class AppUserServiceImpl implements AppUserService {
	@Autowired
	private ApplicationUserDao usrDao;

	
	//Get application user using the email.
    @Override
    @Transactional
    public ApplicationUser getUser(String email) {
        return usrDao.getUser(email);
    }



	

}
