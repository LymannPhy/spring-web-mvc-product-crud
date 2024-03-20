package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    void deleteProductByUuid(@PathVariable String uuid) {
        productService.deleteProductByUuid(uuid);
    }

    @PutMapping("/{uuid}")
    void editProductByUuid(@PathVariable String uuid,
                           @RequestBody ProductEditRequest request) {
        productService.editProductByUuid(uuid, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewProduct(@RequestBody ProductCreateRequest request) {
        System.out.println("REQUEST: " + request);
        productService.createNewProduct(request);
    }

    @GetMapping
    Map<String, Object> findProducts(@RequestParam(required = false, defaultValue = "") String name,
                                     @RequestParam(required = false, defaultValue = "true") Boolean status) {
        return Map.of(
                "message", "Products have been found",
                "data", productService.findProducts(name, status)
        );
    }

    @GetMapping("/{id}")
    Map<String, Object> findProductById(@PathVariable Integer id) {
        return Map.of(
                "message", "Product has been found",
                "data", productService.findProductById(id)
        );
    }

    @GetMapping("/uuid/{uuid}")
    Map<String, Object> findProductByUuid(@PathVariable String uuid) {
        return Map.of(
                "message", "Product has been found",
                "data", productService.findProductByUuid(uuid)
        );
    }



}
