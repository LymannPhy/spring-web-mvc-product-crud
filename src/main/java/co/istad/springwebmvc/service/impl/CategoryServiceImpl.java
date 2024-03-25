package co.istad.springwebmvc.service.impl;

import co.istad.springwebmvc.dto.CategoryResponse;
import co.istad.springwebmvc.model.Category;
import co.istad.springwebmvc.repository.CategoryRepository;
import co.istad.springwebmvc.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> findCateogries() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> new CategoryResponse(category.getName(),
                        category.getDescription()))
                .toList();
    }
}
