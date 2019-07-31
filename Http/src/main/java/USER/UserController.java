package USER;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
    private final HashMap<String ,User> users = new HashMap<>();

    //Spring 通过HTTP请求中的 操作（GET、Post、Put、Delete）+ URL 来确定调用方法来处理请求
    /*
    * 响应Get /users 这样的请求
    * 查询用户列表
    */
    @GetMapping("/users")
    List<User> userList(){
        return new ArrayList<>(users.values());
    }

    /*
    * 响应Get /users{name}的请求
    * 通过User的name查询具体的User对象
    * @param name
    * @return name确定的对象
     */
    @GetMapping("/users/{name}")
    ResponseEntity<User> getUser(@PathVariable String name) {
        if (users.containsKey(name)) {
            return new ResponseEntity<>(users.get(name), HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    * 响应Post/users的请求
    * 添加一个用户到用户列表
    * @param user
    * @return 返回创建成功的User对象
     */
    @PostMapping("/users")
    ResponseEntity<User> newUsers(@RequestBody User user){
        users.put(user.getName(),user);
        return new ResponseEntity<>(users.get(user.getName()), HttpStatus.CREATED);
    }

    /*
    * 响应PUT/users的请求
    * 修改一个用户
    * @param name updateUser
    * @return 修改之后的User对象
     */
    @PutMapping("/users/{name}")
    ResponseEntity<User> updateUser(@PathVariable String name,@RequestBody User updatedUser) {
        if (users.containsKey(name)) {
            User user = users.get(name);
            user.setContent(updatedUser.getContent());
            return new ResponseEntity<>(user,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    * 响应DELETE/users的请求
    * 从用户列表删除用户
    *@ param name
     */
    @DeleteMapping("/users/{name}")
    ResponseEntity<Void> deleteUser(@PathVariable String name) {
        if (users.containsKey(name)) {
            users.remove(name);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
