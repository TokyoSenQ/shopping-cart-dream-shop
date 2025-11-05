package jeka.tsen.shopping_cart_dream_shop.controller;

import jeka.tsen.shopping_cart_dream_shop.exceptions.ProductNotFoundExcpetion;
import jeka.tsen.shopping_cart_dream_shop.exceptions.ResourseNotFoundException;
import jeka.tsen.shopping_cart_dream_shop.model.Product;
import jeka.tsen.shopping_cart_dream_shop.request.AddProductRequest;
import jeka.tsen.shopping_cart_dream_shop.request.ProductUpdateRequest;
import jeka.tsen.shopping_cart_dream_shop.response.ApiResponse;
import jeka.tsen.shopping_cart_dream_shop.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (ProductNotFoundExcpetion e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @GetMapping("product/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById (@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Found", product));
        } catch (ProductNotFoundExcpetion e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product){
        try {
            Product theProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("success", theProduct));
        } catch (ProductNotFoundExcpetion e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest product, @PathVariable Long productId){
        try {
            Product updatedProduct = productService.updateProduct(product, productId);
            return ResponseEntity.ok(new ApiResponse("Update product was successed", updatedProduct));
        } catch (ResourseNotFoundException e) {
          return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct (@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Delete success", product));
        } catch (ProductNotFoundExcpetion e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @GetMapping("/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName) {
        try {
            List<Product> products = productService.getProductByBrandAndName(brandName, productName);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @GetMapping("/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(@RequestParam String category, @RequestParam String brand) {
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/{name}/products")
    public ResponseEntity<ApiResponse> getProductByName (@PathVariable String name){
        try {
            List<Product> products = productService.getProductByName(name);
            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/by-brand")
    public ResponseEntity<ApiResponse> getProductsByBrand(@RequestParam String brand) {
        try {
            List<Product> products = productService.getProductsByBrand(brand);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/{category}/all/products")
    public ResponseEntity<ApiResponse> getAllProductsByCategory(@PathVariable String category) {
        try {
            List<Product> products = productService.getAllProductsByCategory(category);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }


    public ResponseEntity<ApiResponse> countProductsByBrandAndName (@RequestParam String brand, @RequestParam String name) {
        try {
            Long productCount = productService.countProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse("Product count! ", productCount));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }


}
