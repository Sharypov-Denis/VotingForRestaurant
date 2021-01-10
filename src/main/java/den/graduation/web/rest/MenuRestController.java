package den.graduation.web.rest;

import den.graduation.model.Menu;
import den.graduation.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {

    //curl -s -i -X POST -d '{"name":"testmenu","price":"122"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/menu/create/100002
    static final String REST_URL = "/rest/menu";

    @Autowired
    private MenuService menuService;

    @GetMapping("/{id}/{idr}")
    public Menu get(@PathVariable int id, @PathVariable int idr) {
        return menuService.get(id, idr);
    }

    @GetMapping("/getAll/{idr}")
    public List<Menu> getMenuForRestaurant(@PathVariable int idr) {
        return menuService.getAll(idr);
    }

    @GetMapping
    public List<Menu> getAllMenu() {
        return menuService.getAllMenu();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        menuService.delete(id);
    }

    @PostMapping(value = "/create/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Menu> create(@RequestBody Menu menu, @PathVariable int id) {
        Menu created = menuService.create(menu, id);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(menu.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
