package am.shoppingapp.controller;

import am.shoppingapp.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/block")
    public ResponseEntity<?> block(@RequestParam Long userId, @RequestParam boolean block) {
        try {
            adminService.blockUser(userId, block);
            return ResponseEntity.ok("User changed successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/updateProductCategory")
    public ResponseEntity<?> updateProductCategory(@RequestParam Long productId, @RequestParam Long categoryId) {
        try {
            adminService.updateProductCategory(productId, categoryId);
            return ResponseEntity.ok("Product category updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
