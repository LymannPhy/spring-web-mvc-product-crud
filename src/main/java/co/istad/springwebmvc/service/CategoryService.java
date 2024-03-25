package co.istad.springwebmvc.service;

import co.istad.springwebmvc.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findCateogries();


}
