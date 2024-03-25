package co.istad.springwebmvc.service;
import co.istad.springwebmvc.dto.*;

import java.util.List;

public interface ProductService {

    void createNewProduct(ProductCreateRequest request);

    ProductResponse editCategoryById(Integer id, ProductCreateRequest request);

    void editProductByUuid(String uuid, ProductEditRequest request);

    void deleteProductById(Integer id);

    ProductResponse findProductByName(String name);

    ProductResponse findProductById(Integer id);

    List<ProductResponse> findProducts();

    ProductResponse findProductByUuid(String uuid);

}