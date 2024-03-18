package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

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
