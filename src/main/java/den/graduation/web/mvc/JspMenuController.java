package den.graduation.web.mvc;


import den.graduation.model.Menu;
import den.graduation.model.Restaurant;
import den.graduation.service.MenuService;
import den.graduation.service.RestaurantService;
import den.graduation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;


@Controller
@RequestMapping(value = "/menu")
public class JspMenuController {
    private static final Logger log = LoggerFactory.getLogger(JspMenuController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        menuService.delete(getId(request));
        log.info("delete menu {}", getId(request));
        return "redirect:/restaurants";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        return "menuForm";
    }

    private Integer restaurantId;

    @GetMapping("/createMenu")
    public String create(Model model, HttpServletRequest request) {
        model.addAttribute("menu", new Menu("", 0, null));
        restaurantId = getId(request);
        return "menuForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Menu menu = new Menu(
                request.getParameter("newMenu"),
                Integer.parseInt(request.getParameter("price")),
                LocalDateTime.parse(request.getParameter("date")));

        Restaurant restaurant = restaurantService.getOne(restaurantId);
        menu.setRestaurant(restaurant);

        menuService.create(menu, restaurantId);
        log.info("updateOrCreate menu");
        return "redirect:/restaurants";
    }

    private int getId(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF8");
        } catch (Exception ex) {
            System.out.println("ERRORS");
        }
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}