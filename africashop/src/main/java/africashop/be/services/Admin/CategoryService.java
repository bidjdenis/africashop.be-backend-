package africashop.be.services.Admin;

import africashop.be.dtos.CategoryDto;
import africashop.be.entities.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryDto categoryDto );

    List<Category> getAllCategory();

    Category getOneCategory(Long id);

    void deleteCategory(Long id);

    Category updateCategory(Category category, Long id);


}
