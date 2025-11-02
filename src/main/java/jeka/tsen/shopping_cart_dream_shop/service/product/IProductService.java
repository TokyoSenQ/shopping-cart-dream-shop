package jeka.tsen.shopping_cart_dream_shop.service.product;

import jeka.tsen.shopping_cart_dream_shop.model.Product;
import jeka.tsen.shopping_cart_dream_shop.request.AddProductRequest;
import jeka.tsen.shopping_cart_dream_shop.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct (AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long productId);

    List<Product> getAllProducts();
    List<Product> getProductByName(String name);
    List<Product> getAllProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);


}
