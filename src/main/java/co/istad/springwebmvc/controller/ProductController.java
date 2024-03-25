package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.dto.*;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the products",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "products not found",
                    content = @Content)
    })

    @GetMapping
    List<ProductResponse> findProducts(){
        return productService.findProducts();
    }

    @GetMapping("{id}")
    ProductResponse findProductById(@PathVariable Integer id){
        return productService.findProductById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewProduct(@Valid @RequestBody ProductCreateRequest request){
        productService.createNewProduct(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteProductById(@PathVariable Integer id){
        productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    ProductResponse editProductById(@PathVariable Integer id, @Valid @RequestBody ProductCreateRequest request){
        return productService.editCategoryById(id, request);
    }

}