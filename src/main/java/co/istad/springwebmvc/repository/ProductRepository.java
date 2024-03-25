package co.istad.springwebmvc.repository;

import co.istad.springwebmvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
    Boolean existsByName(String name);
}