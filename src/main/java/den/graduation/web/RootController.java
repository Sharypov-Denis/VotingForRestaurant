package den.graduation.web;

import den.graduation.SecurityUtil;
import den.graduation.service.MenuService;
import den.graduation.service.RestaurantService;
import den.graduation.service.UserService;
import den.graduation.service.VotingService;
import den.graduation.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private static VotingService votingService;

    @GetMapping("/")
    public String root() {
        //  return "index";
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.authUserId();
        return "redirect:restaurants";
    }

    @GetMapping("/restaurants")
    public String getRestaurant(Model model, HttpServletRequest request) {
        //String vote;
        model.addAttribute("restaurants",
                restaurantService.getAllRestaurants());
        model.addAttribute("time", DataUtil.getDateNow());
       // model.addAttribute("status", vote);
        return "restaurants";
    }

}
