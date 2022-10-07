package springsecurity.security.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springsecurity.security.appuser.ApplicationUser;
import springsecurity.security.dao.ApplicationUserDao;
import springsecurity.security.services.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService {
	@Autowired
	private ApplicationUserDao usrDao;

	// Get application user using the email.
	@Override
	@Transactional
	public ApplicationUser getUser(String email) {
		return usrDao.getUser(email);
	}

	@Override
	@Transactional
	public void saveAppUser(ApplicationUser appUser) {
		// Get the date in format defined
		SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");

		// Get the date in string format
		String date = sdate.format(new Date());

		// Instantiate the BCrypt password algorithm
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		// Hash and set the password for persistence
		appUser.setPassword(encoder.encode(appUser.getPassword()));
		appUser.setCreatedDate(date);
		appUser.setCreatedBy("1");
		appUser.setEnabled("1");
		appUser.setLocked("YES");
		usrDao.saveAppUser(appUser);

	}

}
