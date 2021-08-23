package am.shoppingapp.controller;

import am.shoppingapp.config.jwt.JwtProvider;
import am.shoppingapp.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import am.shoppingapp.service.UserService;

@RestController
@RequestMapping("/security")
public class SecurityController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public SecurityController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }


    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestParam String login, @RequestParam String pass) {
        try {
            User user = userService.findByLoginAndPassword(login, pass);
            String token = jwtProvider.generateToken(user.getName());
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam String login, @RequestParam String pass) {
        User user = new User();
        user.setPass(pass);
        user.setName(login);
        userService.saveUser(user);
        return ResponseEntity.ok("OK");
    }

}
