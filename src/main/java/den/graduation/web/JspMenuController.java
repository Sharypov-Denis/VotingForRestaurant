package den.graduation.web;


import den.graduation.SecurityUtil;
import den.graduation.model.Menu;
import den.graduation.model.Restaurant;
import den.graduation.repository.datajpa.DataJPAVotingRepository;
import den.graduation.service.MenuService;
import den.graduation.service.RestaurantService;
import den.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Objects;


@Controller
@RequestMapping(value = "/menu")
public class JspMenuController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        menuService.delete(getId(request));
        //exerciseRestController.delete(getId(request));
        return "redirect:/restaurants";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        // model.addAttribute("menu", menuService.get((getId(request)));
        //model.addAttribute("exercise", exerciseRestController.get(getId(request)));
        return "menuForm";
    }

    private Integer restaurantId;

    @GetMapping("/createMenu")
    public String create(Model model, HttpServletRequest request) {
        //model.addAttribute("restaurant", new Restaurant("", "", 0));
        model.addAttribute("menu", new Menu("", 0));
        restaurantId=getId(request);
        return "menuForm";
    }


    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Menu menu = new Menu(
                request.getParameter("newMenu"),
                Integer.parseInt(request.getParameter("price")));
        Restaurant restaurant = restaurantService.getOne(restaurantId);
        menu.setRestaurant(restaurant);

        menuService.create(menu, restaurantId);
        /*
        if (request.getParameter("id").isEmpty()) {
            menuService.create(menu, restaurant.getId());

        } else {
            menuService.update(menu,getId(request));
        }

         */

        return "redirect:/restaurants";
    }

    private int getId(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF8");
        }
        catch (Exception ex) {
            System.out.println("ERRORS");
        }
        System.out.println("test start- " +  " !!!");
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        System.out.println("test - " + paramId + " !!!");
        return Integer.parseInt(paramId);
    }

}