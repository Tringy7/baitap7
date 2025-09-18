package vn.iotstar.service.impl;

import vn.iotstar.entity.Category;
import vn.iotstar.repository.CategoryRepository;
import vn.iotstar.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired; // hoặc @RequiredArgsConstructor nếu dùng Lombok
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repo;

    @Override
    public Page<Category> search(String keyword, Pageable pageable) {
        if (keyword == null || keyword.isBlank()) {
            return repo.findAll(pageable);
        }
        return repo.findByNameContainingIgnoreCase(keyword.trim(), pageable);
    }

    @Override
    public Category save(Category category) {
        return repo.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}