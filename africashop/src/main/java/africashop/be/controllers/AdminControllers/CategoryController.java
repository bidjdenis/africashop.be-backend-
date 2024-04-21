package africashop.be.controllers.AdminControllers;

import africashop.be.dtos.CategoryDto;
import africashop.be.entities.Category;
import africashop.be.services.Admin.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/saveCategory")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){
       Category category = categoryService.createCategory(categoryDto);
       return  ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping("/category")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategory();
    }
    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return this.getCategoryById(id);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public void deleteCategory(@PathVariable Long id){
        this.categoryService.deleteCategory(id);
    }

    @PutMapping("/category/update/{id}")
    public Category updateCategory(@PathVariable Long id, Category category){
        return categoryService.updateCategory(id,category);
    }
}
