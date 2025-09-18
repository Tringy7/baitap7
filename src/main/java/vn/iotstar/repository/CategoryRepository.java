package vn.iotstar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.iotstar.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Tìm kiếm theo tên (phân trang, không phân biệt hoa thường)
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);
}