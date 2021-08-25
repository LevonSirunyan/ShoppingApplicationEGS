package am.shoppingapp.service;

import javax.persistence.EntityNotFoundException;

public interface AdminService {
    void blockUser(Long userId, boolean block) throws EntityNotFoundException;

    void updateProductCategory(Long productId, Long categoryId) throws EntityNotFoundException;
}
