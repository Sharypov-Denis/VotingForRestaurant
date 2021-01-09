package den.graduation.web.rest;


import den.graduation.model.Restaurant;
import den.graduation.service.RestaurantService;
import den.graduation.web.AbstractRestaurantController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/restaurants";
    // create Restaurant "curl -s -X POST -d '{"name":"Новый ресторан","numberOfVotes":10}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/restaurants/create --user admin@gmail.com:admin"
    //                    curl -s -i -X POST -d '{"name":"Новый ресторан","numberOfVotes":10}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/restaurants/create
    //                    curl -s -i -X POST -d '{"name":"Новый ресторан","numberOfVotes":10}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/restaurants/create --user admin@gmail.com:admin
    @Autowired
    private RestaurantService service;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return super.getAllRestaurants();
    }

    @Override
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return service.getOne(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        super.update(restaurant, id);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant r) {
        Restaurant restaurant = super.create(r);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(r.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(restaurant);
    }

}
