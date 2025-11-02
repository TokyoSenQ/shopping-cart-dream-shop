package jeka.tsen.shopping_cart_dream_shop.request;

import jeka.tsen.shopping_cart_dream_shop.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
}



//2) differenza tra Categoria e Prodotto. Metodi addCategory e updateCategory e ProductUpdateRequest
