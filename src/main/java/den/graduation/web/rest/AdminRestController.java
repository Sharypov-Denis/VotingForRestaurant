package den.graduation.web.rest;

import den.graduation.model.User;
import den.graduation.service.UserService;
import den.graduation.web.AbstractUserController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Endpoints for Retrieving, Updating and Deleting of Users.",
        tags = {"admin-ui-controller"})
@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController extends AbstractUserController {
    static final String REST_URL = "/rest/admin/users";

    @Autowired
    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Find Retrieving All Users", tags = { "admin-ui-controller" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response=List.class )  })
        @GetMapping
    public List<User> getAll() {
        return super.getAll();
    }

    @ApiOperation(value = "Find User by ID", notes = "Returns a single User", tags = { "admin-ui-controller" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response=User.class),
            @ApiResponse(code = 404, message = "User not found") })
    @Override
    @GetMapping("/get/{id}")
    public User get(@PathVariable int id) {
        return super.get(id);
    }

    @ApiOperation(value = "Deletes a user", tags = { "admin-ui-controller" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 404, message = "User not found") })
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @ApiOperation(value = "Update an existing users", tags = { "admin-ui-controller" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, @PathVariable int id) {
        super.update(user, id);
    }

    @ApiOperation(value = "Find User by Email", notes = "Returns a single User by Email", tags = { "admin-ui-controller" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response=User.class),
            @ApiResponse(code = 404, message = "User not found") })
    @GetMapping("/by")
    public User getByMail(@RequestParam String email) {
        return super.getByMail(email);
    }
}