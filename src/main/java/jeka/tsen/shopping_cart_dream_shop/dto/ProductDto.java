package jeka.tsen.shopping_cart_dream_shop.dto;


import jeka.tsen.shopping_cart_dream_shop.model.Category;
import jeka.tsen.shopping_cart_dream_shop.model.Image;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
    private List<ImageDto> images;
}
