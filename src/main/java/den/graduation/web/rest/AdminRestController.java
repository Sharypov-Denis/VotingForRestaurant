package den.graduation.web.rest;

import den.graduation.model.User;
import den.graduation.UserService;
import den.graduation.web.AbstractUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController extends AbstractUserController {
    static final String REST_URL = "/rest/admin/users";
    //get All Users
    //curl -s http://localhost:8080/rest/admin/users --user admin@gmail.com:admin
    //get Users 100001
    //curl -s http://localhost:8080/rest/admin/users/get/100001 --user admin@gmail.com:admin
    //delete User 100000
    //curl -s -X DELETE http://localhost:8080/rest/admin/users/delete/100000 --user admin@gmail.com:admin
    //register Users
    //curl -s -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/users/register --user admin@gmail.com:admin
    //curl -s -i -X POST -d '{"name":"New User","email":"testuser@mail.ru","password":"password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/users/register --user admin@gmail.com:admin
    @Autowired
    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/get/{id}")
    public User get(@PathVariable int id) {
        return super.get(id);
    }
/*
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody User user) {
        User created = super.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

 */

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = create(user);
        URI ofNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .build().toUri();
        return ResponseEntity.created(ofNewResource).body(createdUser);
    }

    public User create(User user) {
        return userService.create(user);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, @PathVariable int id) {
        super.update(user, id);
    }

    @GetMapping("/by")
    public User getByMail(@RequestParam String email) {
        return super.getByMail(email);
    }
}