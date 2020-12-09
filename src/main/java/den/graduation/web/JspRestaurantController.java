package den.graduation.web;

import den.graduation.SecurityUtil;
import den.graduation.model.Restaurant;
import den.graduation.model.Voting;
import den.graduation.service.MenuService;
import den.graduation.service.RestaurantService;
import den.graduation.service.UserService;
import den.graduation.service.VotingService;
import den.graduation.util.DataUtil;
import den.graduation.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/restaurants")
public class JspRestaurantController extends RestaurantRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private VotingService votingService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserValidator validatorRest;

    /* можно контроллер автоварить а можно через супер как сейчас
    @Autowired
    private RestaurantRestController exerciseRestController;

     */

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        //exerciseRestController.delete(getId(request));
        return "redirect:/restaurants";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("restaurant", super.get(getId(request)));
        //model.addAttribute("exercise", exerciseRestController.get(getId(request)));
        return "restaurantForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("restaurant", new Restaurant("", 0));
        // model.addAttribute("menu", new Menu("",  0));
        model.addAttribute("register", true);
        return "restaurantForm";
    }


        @PostMapping
        public String updateOrCreate(HttpServletRequest request) {
            Restaurant restaurant = new Restaurant(
                    request.getParameter("name"),
                    Integer.parseInt(request.getParameter("numberOfVotes")));

            if (request.getParameter("id").isEmpty()) {
                super.create(restaurant);
            } else {
                super.update(restaurant, getId(request));

            }
            return "redirect:/restaurants";
        }

    /*
    @PostMapping
    public String updateOrCreate(HttpServletRequest request, @Valid @ModelAttribute("restaurant") Restaurant rest, BindingResult result, ModelMap model) {
       Restaurant restaurant = new Restaurant(
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("numberOfVotes")));



        validatorRest.validate1(rest, result);
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "restaurantForm";
        }

        if (request.getParameter("id").isEmpty()) {
            super.create(restaurant);
        } else {
            super.update(restaurant, getId(request));

        }
        return "redirect:/restaurants";
    }

     */

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        System.out.println("test - " + paramId + " !!!");
        return Integer.parseInt(paramId);
    }

    @GetMapping("/voting")
    public String updateOrCreateVoting(HttpServletRequest request, Model model) {
        Voting voting = new Voting();
        int userId = SecurityUtil.authUserId();
        List<Voting> list = votingService.getAllByUser(userId);
        System.out.println(list);


        if (list != null && DataUtil.Utilll(list) == true) {
            System.out.println("вы уже голосовали и проголосовать не сможете!!!");
        } else {
            System.out.println("вы можете голосовать!!");
            votingService.create(voting, getId(request), userId);
            restaurantService.updateById(getId(request));
        }
        return "redirect:/restaurants";
    }
}
