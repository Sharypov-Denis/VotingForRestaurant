package den.graduation.web;

import den.graduation.model.User;
import den.graduation.service.UserService;
import den.graduation.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileUIController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("register", true);
        return "profile";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid @ModelAttribute("user")User user, BindingResult result, SessionStatus status, ModelMap model) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "profile";
        }
        userService.create(user);
        status.setComplete();
        return "redirect:/login";
    }
}