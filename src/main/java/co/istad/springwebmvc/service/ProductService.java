package co.istad.springwebmvc.service;

import co.istad.springwebmvc.dto.ProductDto;
import co.istad.springwebmvc.model.Product;

import java.util.List;

public interface ProductService {

    List<ProductDto> findProducts(String name, Boolean status);

    ProductDto findProductById(Integer id);

    ProductDto findProductByUuid(String uuid);
}
