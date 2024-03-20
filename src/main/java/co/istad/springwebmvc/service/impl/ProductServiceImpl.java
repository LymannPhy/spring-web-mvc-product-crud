package co.istad.springwebmvc.service.impl;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private List<Product> products;

    public ProductServiceImpl() {
        products = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(1);
        p1.setUuid(UUID.randomUUID().toString());
        p1.setName("iPhone 14 Pro Max");
        p1.setPrice(1300.0);
        p1.setQty(1);
        p1.setImportedDate(LocalDateTime.now());
        p1.setStatus(true);
        Product p2 = new Product();
        p2.setId(2);
        p2.setUuid(UUID.randomUUID().toString());
        p2.setName("macBook M3 RAM 30GB");
        p2.setPrice(2600.0);
        p2.setQty(2);
        p2.setImportedDate(LocalDateTime.now());
        p2.setStatus(true);
        Product p3 = new Product();
        p3.setId(3);
        p3.setUuid(UUID.randomUUID().toString());
        p3.setName("macBook M3 Pro RAM 30GB");
        p3.setPrice(2500.0);
        p3.setQty(1);
        p3.setImportedDate(LocalDateTime.now());
        p3.setStatus(false);
        Product p4 = new Product();
        p4.setId(3);
        p4.setUuid(UUID.randomUUID().toString());
        p4.setName("macBook M3 RAM 18GB");
        p4.setPrice(2200.0);
        p4.setQty(1);
        p4.setImportedDate(LocalDateTime.now());
        p4.setStatus(true);
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
    }

    @Override
    public void deleteProductByUuid(String uuid) {

        products = products.stream()
                .filter(product -> !product.getUuid().equals(uuid))
                .collect(Collectors.toList());

        log.info("Affected row: {}", 1);

    }

    @Override
    public void editProductByUuid(String uuid, ProductEditRequest request) {
        // Check UUID if exists
        long count = products.stream()
                .filter(product -> product.getUuid().equals(uuid))
                .peek(oldProduct -> {
                    oldProduct.setName(request.name());
                    oldProduct.setPrice(request.price());
                }).count();
        System.out.println("Affected row = " + count);
    }

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        Product newProduct = new Product();
        newProduct.setName(request.name());
        newProduct.setPrice(request.price());
        newProduct.setQty(request.qty());
        newProduct.setId(products.size() + 1);
        newProduct.setUuid(UUID.randomUUID().toString());
        newProduct.setImportedDate(LocalDateTime.now());
        newProduct.setStatus(true);
        products.add(newProduct);
    }

    @Override
    public List<ProductResponse> findProducts(String name, Boolean status) {
        return products.stream()
                .filter(product -> product.getName().toLowerCase()
                        .contains(name.toLowerCase()) && product.getStatus().equals(status))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                ))
                .toList();
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id) &&
                        product.getStatus().equals(true))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                ))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public ProductResponse findProductByUuid(String uuid) {
        return products.stream()
                .filter(product -> product.getUuid().equals(uuid) &&
                        product.getStatus().equals(true))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                ))
                .findFirst()
                .orElseThrow();
    }
}
