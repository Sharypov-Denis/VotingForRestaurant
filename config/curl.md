### curl samples
> For windows use `Git Bash`


#### get All Users: 
curl -s http://localhost:8080/rest/admin/users --user admin@gmail.com:admin

#### get Users with id = 100001:  
`curl -s http://localhost:8080/rest/admin/users/get/100001 --user admin@gmail.com:admin

#### delete User with id = 100000:  
`curl -s -X DELETE http://localhost:8080/rest/admin/users/delete/100000 --user admin@gmail.com:admin


#### get All menu:  
`curl -s http://localhost:8080/rest/menu

#### get MenuForRestaurant with id = 100001 and menu id = 10001:  
`curl -s http://localhost:8080/rest/menu/10001/100001

#### get MenuForRestaurant with id = 100001:  
`curl -s http://localhost:8080/rest/menu/getAll/100001

#### delete menu with id = 100001:  
`curl -s -X DELETE http://localhost:8080/rest/menu/delete/10001


#### get All Restaurants:  
`curl -s http://localhost:8080/rest/restaurants

#### get Restaurants with id = 100002:  
`curl -s http://localhost:8080/rest/restaurants/100002

#### delete Restaurant with id = 100001: 
`curl -s -X DELETE http://localhost:8080/rest/restaurants/delete/100001 --user admin@gmail.com:admin

