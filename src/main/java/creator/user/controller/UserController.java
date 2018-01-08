package creator.user.controller;

import creator.user.model.User;
import creator.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/save")
    private String saveUser() {
        userService.saveUser(new User("paalginaa@gmail.com", "thebest", "Gina", "Paál"));
        return "textarea";
    }
}
