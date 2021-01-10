package den.graduation.web.rest;

import den.graduation.model.User;
import den.graduation.repository.UserRepository;
import den.graduation.service.UserService;
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

    @Autowired
    private UserRepository userRepository;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }
/*
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestParam User user) {
        User created = userService.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

 */

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
        //@ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<Void> createUser(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
        // return ResponseEntity.ok().build();
    }
}