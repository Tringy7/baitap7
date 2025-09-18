package vn.iotstar.controller;

import vn.iotstar.entity.Category;
import vn.iotstar.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Hiển thị danh sách + tìm kiếm + phân trang
    @GetMapping
    public String listCategories(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "q", required = false) String keyword,
            Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Category> pageData = categoryService.search(keyword, pageable);

        model.addAttribute("pageData", pageData);
        model.addAttribute("q", keyword);

        return "categories/list"; // templates/categories/list.html
    }


    // Mở form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/add-edit"; // templates/categories/add-edit.html
    }

    // Lưu Category (thêm mới hoặc cập nhật)
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    // Mở form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Optional<Category> opt = categoryService.findById(id);
        if (opt.isPresent()) {
            model.addAttribute("category", opt.get());
            return "categories/add-edit";
        }
        return "redirect:/categories";
    }

    // Xóa Category
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }

}
