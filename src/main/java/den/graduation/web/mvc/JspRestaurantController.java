package den.graduation.web.mvc;

import den.graduation.SecurityUtil;
import den.graduation.model.Restaurant;
import den.graduation.model.Voting;
import den.graduation.service.RestaurantService;
import den.graduation.service.VotingService;
import den.graduation.util.DataUtil;
import den.graduation.web.AbstractRestaurantController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private VotingService votingService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public String getRestaurant(Model model) {
        List<Voting> list = votingService.getAllByUser(SecurityUtil.authUserId());
        String vote = DataUtil.userVotesStatus(list);
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        restaurantService.delete(getId(request));
        log.info("delete restaurant {}", getId(request));
        return "redirect:/restaurants";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("restaurant", restaurantService.getOne(getId(request)));
        return "restaurantForm";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("restaurant", new Restaurant("", 0));
        model.addAttribute("register", true);
        return "restaurantForm";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String updateOrCreate(@RequestParam(value = "id") Integer id, HttpServletRequest request) {
        Restaurant restaurant = new Restaurant(
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("numberOfVotes")));
        restaurant.setId(id);
        restaurantService.update(restaurant);
        log.info("updateOrCreate restaurant {}", id);
        return "redirect:/restaurants";
    }

    @GetMapping("/voting")
    public String updateOrCreateVoting(HttpServletRequest request) {
        DataUtil.createAndUpdateVoting(getId(request));
        log.info("updateOrCreateVoting restaurant{}", getId(request));
        return "redirect:/restaurants";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
