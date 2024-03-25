package co.istad.springwebmvc.repository;

import co.istad.springwebmvc.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
