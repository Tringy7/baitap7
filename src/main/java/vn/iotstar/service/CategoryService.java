package vn.iotstar.service;
import vn.iotstar.entity.Category;                // Entity
import org.springframework.data.domain.Page;      // Phân trang
import org.springframework.data.domain.Pageable;

import java.util.Optional;   

public interface CategoryService {
    Page<Category> search(String keyword, Pageable pageable);
    Category save(Category c);
    Optional<Category> findById(Long id);
    void deleteById(Long id);
}
