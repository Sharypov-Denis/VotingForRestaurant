package den.graduation.web.rest;

import den.graduation.model.Menu;
import den.graduation.model.User;
import den.graduation.service.MenuService;
import den.graduation.service.RestaurantService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Api("Endpoints for Retrieving, Updating and Deleting of Menu.")
@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {

    static final String REST_URL = "/rest/menu";

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurantService;

    @ApiOperation(value = "Find menu by ID", notes = "Returns a single menu")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response= User.class),
            @ApiResponse(code = 404, message = "User not found") })
    @GetMapping("/{id}/{idr}")
    public Menu get(@PathVariable int id, @PathVariable int idr) {
        return menuService.get(id, idr);
    }

    @ApiOperation(value = "Find Retrieving All menu for Restaurant ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response=List.class )  })
    @GetMapping("/getAll/{idr}")
    public List<Menu> getMenuForRestaurant(
            @ApiParam("Id of the Restaurant to be update. Cannot be empty.")
            @PathVariable int idr) {
        return menuService.getAll(idr);
    }

    @ApiOperation(value = "Find Retrieving All menu")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response=List.class )  })
    @GetMapping
    public List<Menu> getAllMenu() {
        return menuService.getAllMenu();
    }

    @ApiOperation(value = "Deletes a menu")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 404, message = "Menu not found") })
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        menuService.delete(id);
    }

    @ApiOperation(value = "Add a new Menu")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Menu created"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 409, message = "Menu already exists") })
    @PostMapping(value = "/create/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Menu> create(@RequestBody Menu menu,
                                       @ApiParam("Id of the Restaurant to be update. Cannot be empty.")
                                       @PathVariable int id) {
        menu.setRestaurant(restaurantService.getOne(id));
        Menu created = menuService.create(menu, id);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(menu.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
