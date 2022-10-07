package springsecurity.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springsecurity.security.appuser.ApplicationUser;
import springsecurity.security.services.AppUserService;

@Controller
public class RegisterController {
	
	@Autowired
	private AppUserService appUserService;
	
	@RequestMapping("/register")
	 public String registerForm(Model model){
        model.addAttribute("appUser", new ApplicationUser());
        return "registerForm";
    }

	@PostMapping("/register")
    public String registerMember(@Validated ApplicationUser appUser){
		appUserService.saveAppUser(appUser);
        return "login";
    }
}
