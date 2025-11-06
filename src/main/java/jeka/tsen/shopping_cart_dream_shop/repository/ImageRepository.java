package jeka.tsen.shopping_cart_dream_shop.repository;

import jeka.tsen.shopping_cart_dream_shop.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductId(Long id);
}
