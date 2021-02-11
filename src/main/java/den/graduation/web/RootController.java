package den.graduation.web;

import den.graduation.SecurityUtil;
import den.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

@ApiIgnore
@Controller
public class RootController {

    private AtomicInteger visitsCounter = new AtomicInteger(0);
    public void init() {
        visitsCounter.getAndIncrement();
        System.out.println("Счетчик посещений страницы: " + visitsCounter);
    }

    /*another variant
    private volatile int visitsCounter;
    public void init() {
        visitsCounter = 0;
    }
    synchronized void increaseAmountOfVisits() {
        visitsCounter++;
    }
    */

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        //increaseAmountOfVisits();
        init();
        return "login";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.authUserId();
        return "redirect:restaurants";
    }
}
