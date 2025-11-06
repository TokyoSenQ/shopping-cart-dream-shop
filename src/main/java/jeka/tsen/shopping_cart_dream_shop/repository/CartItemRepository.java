package jeka.tsen.shopping_cart_dream_shop.repository;

import jeka.tsen.shopping_cart_dream_shop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    void deleteAllByCartId(Long id);
}
