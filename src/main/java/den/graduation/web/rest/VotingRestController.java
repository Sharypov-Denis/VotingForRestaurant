package den.graduation.web.rest;


import den.graduation.SecurityUtil;
import den.graduation.model.Voting;
import den.graduation.service.RestaurantService;
import den.graduation.service.UserService;
import den.graduation.service.VotingService;
import den.graduation.util.DataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Api("Endpoints for Retrieving, Updating and Deleting of Voting.")
@RestController
@RequestMapping(value = VotingRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VotingRestController {

    static final String REST_URL = "/rest/vote";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private VotingService votingService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Find Retrieving All Voting")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response=List.class )  })
    @GetMapping("/getAllByUser/{id}")
    public List<Voting> getAllByUser(@PathVariable int id) {
        return votingService.getAllByUser(id);
    }

    @ApiOperation(value = "Deletes a Voting")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 404, message = "Menu not found") })
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        votingService.delete(id);
    }

    @ApiOperation(value = "Add a new Voting")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Voting created"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 409, message = "Voting already exists") })
    @PostMapping(value = "/create/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Voting> create(@RequestBody Voting voting, @PathVariable int id) {
        voting.setUser(userService.get(SecurityUtil.authUserId()));
        voting.setRestaurant(restaurantService.getOne(id));
        DataUtil.createAndUpdateVoting(id);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(voting.getId()).toUri();
        return ResponseEntity.ok().build();
    }

}
