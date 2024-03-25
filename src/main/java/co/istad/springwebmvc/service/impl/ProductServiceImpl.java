package co.istad.springwebmvc.service.impl;

import co.istad.springwebmvc.dto.*;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.repository.ProductRepository;
import co.istad.springwebmvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        if(productRepository.existsByName(request.name())){
            throw new ResponseStatusException(
                    HttpStatus.CREATED,
                    "Product name already existed...!"
            );
        }
        Product product = new Product();
        product.setUuid(UUID.randomUUID().toString());
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        product.setImportedDate(LocalDateTime.now());
        product.setStatus(true);
        productRepository.save(product);
    }

    @Override
    public ProductResponse editCategoryById(Integer id, ProductCreateRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Product has been not found...!"
                        ));
        product.setUuid(UUID.randomUUID().toString());
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        product.setImportedDate(LocalDateTime.now());
        product.setStatus(true);
        productRepository.save(product);
        return this.findProductById(id);
    }

    @Override
    public void editProductByUuid(String uuid, ProductEditRequest request) {

    }

    @Override
    public void deleteProductById(Integer id) {
        if(!productRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Products has been not found...!"
            );
        }
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse findProductByName(String name) {
        return null;
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Product has been not found"
                        ));
        return new ProductResponse(product.getUuid(),product.getName(),product.getPrice(),product.getQty());
    }

    @Override
    public List<ProductResponse> findProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product ->
                        new ProductResponse(product.getUuid(),product.getName(),product.getPrice(),product.getQty()))
                .toList();
    }

    @Override
    public ProductResponse findProductByUuid(String uuid) {
        return null;
    }
}