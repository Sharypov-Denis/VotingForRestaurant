package den.graduation.web.rest;

import den.graduation.model.User;
import den.graduation.repository.UserRepository;
import den.graduation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    static final String REST_URL = "/rest/users";
    //curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/users/register
    //curl -s -i -X POST -d '{"name":"New User","email":"testuser@mail.ru","password":"testpassword"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/users/register

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
    public ResponseEntity<User> register(@RequestBody User user) {
        User created = create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
*/

       @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
       //@ResponseStatus(value = HttpStatus.CREATED)
       public ResponseEntity<User> createUser(User user) {
           User newUser = userRepository.save(user);
          // URI ofNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                   //.path(REST_URL)
                  // .build().toUri();
           return ResponseEntity.ok().body(user);
          // return ResponseEntity.created(ofNewResource).body(newUser);
       }

    public User create(User user) {
        return userService.create(user);
    }

}