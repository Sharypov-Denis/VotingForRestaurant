package den.graduation.web.rest;


import den.graduation.SecurityUtil;
import den.graduation.model.Voting;
import den.graduation.service.MenuService;
import den.graduation.service.RestaurantService;
import den.graduation.service.UserService;
import den.graduation.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = VotingRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VotingRestController {

    static final String REST_URL = "/rest/vote";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private VotingService votingService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @GetMapping("/getAllByUser/{id}")
    public List<Voting> getAllByUser(@PathVariable int id) {
        return votingService.getAllByUser(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        votingService.delete(id);
    }

    @PostMapping(value = "/create/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Voting> create(@RequestBody Voting voting, @PathVariable int id) {
        voting.setUser(userService.get(SecurityUtil.authUserId()));
        voting.setRestaurant(restaurantService.getOne(id));
        Voting votingCreated = votingService.create(voting, id, SecurityUtil.authUserId());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(voting.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(votingCreated);
    }

}
