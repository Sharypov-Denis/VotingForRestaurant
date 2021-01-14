package den.graduation.web.rest;

import den.graduation.model.User;
import den.graduation.service.UserService;
import den.graduation.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = UserRestController.REST_URL)
public class UserRestController {

    static final String REST_URL = "/rest/users";

    @Autowired
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createUser(@RequestBody User user) {
        ValidationUtil.checkNewUser(user);
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}