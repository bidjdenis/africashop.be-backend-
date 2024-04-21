package africashop.be.services.Admin;

import africashop.be.Repositories.CategoryRepo;
import africashop.be.dtos.CategoryDto;
import africashop.be.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    @Override
    public Category createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getOneCategory(Long id) {
        return categoryRepo.findById(id).get();
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category category1 = categoryRepo.findById(id).get();
        category1.setName(category.getName());
        return categoryRepo.save(category1);
    }
}
