package den.graduation.web.mvc;

import den.graduation.SecurityUtil;
import den.graduation.model.Restaurant;
import den.graduation.model.Voting;
import den.graduation.MenuService;
import den.graduation.RestaurantService;
import den.graduation.UserService;
import den.graduation.VotingService;
import den.graduation.util.DataUtil;
import den.graduation.util.UserValidator;
import den.graduation.util.VotingUtil;
import den.graduation.web.AbstractRestaurantController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/restaurants")
public class JspRestaurantController extends AbstractRestaurantController {
    private static final Logger log = LoggerFactory.getLogger(JspRestaurantController.class);

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

    @GetMapping
    public String getRestaurant(Model model) {
        List<Voting> list = votingService.getAllByUser(SecurityUtil.authUserId());
        String vote = VotingUtil.createOrUpdateVoting(list);

        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        model.addAttribute("time", DataUtil.getDateNow());
        model.addAttribute("status", vote);
        log.info("get all restaurants");
        return "restaurants";
    }

    @GetMapping("/sorry")
    public String sorry() {
        return "sorry";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        System.out.println("проверка id пользователя " + SecurityUtil.authUserId());
        System.out.println("проверка id ресторана " + getId(request));
        restaurantService.delete(getId(request));
        return "redirect:/restaurants";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        // model.addAttribute("restaurant", super.get(getId(request)));
        model.addAttribute("restaurant", restaurantService.getOne(getId(request)));
        System.out.println("проверка входящего ID для функции update:" + getId(request));
        System.out.println("Проверка, приходит ли объект: " + restaurantService.getOne(getId(request)));
        return "restaurantForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("restaurant", new Restaurant("", 0));
        model.addAttribute("register", true);
        return "restaurantForm";
    }

    @PostMapping
    public String updateOrCreate(@RequestParam(value = "id") Integer id, HttpServletRequest request) {
        Restaurant restaurant = new Restaurant(
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("numberOfVotes")));
        System.out.println("ПРОВЕРКА" + id);
/*
        if (id == null || id == 0) {
            super.create(restaurant);
        } else {
            System.out.println("проверка ID для функции update метода пост:");
            super.update(restaurant, id);
        }

 */
        restaurant.setId(id);
        restaurantService.update(restaurant);
        log.info("updateOrCreate restaurant {}", id);
        return "redirect:/restaurants";
    }

    @GetMapping("/voting")
    public String updateOrCreateVoting(HttpServletRequest request) {
        Voting voting = new Voting();
        List<Voting> list = votingService.getAllByUser(SecurityUtil.authUserId());

        if (list != null && DataUtil.UpdateVoting(list) == true) {
            //System.out.println("вы уже проголосовали, но возможно, получиться проголосовать еще - проверяем");
            if (DataUtil.isVoting(list) == false) {
                //System.out.println("вы уже голосовали и проголосовать не сможете");
            } else {
                //System.out.println("вы уже проголосовали, но можете проголосовать снова");
                votingService.create(voting, getId(request), SecurityUtil.authUserId());
            }
        } else {
            votingService.create(voting, getId(request), SecurityUtil.authUserId());
            restaurantService.updateById(getId(request));
        }
        log.info("updateOrCreateVoting restaurant{}", getId(request));
        return "redirect:/restaurants";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
