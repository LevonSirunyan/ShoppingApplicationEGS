package am.shoppingapp.controller;

import am.shoppingapp.entity.User;
import am.shoppingapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/block")
    public ResponseEntity<?> block(@RequestParam Long userId, @RequestParam boolean block) {
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBlocked(block);
            userService.save(user);
            return ResponseEntity.ok("User changed successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("User by %s id not found", userId));
    }

}
