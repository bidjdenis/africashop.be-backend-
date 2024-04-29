package africashop.be.services.Member;

import africashop.be.Repositories.CategoryRepo;
import africashop.be.dtos.CategoryDto;
import africashop.be.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoriesServiceImpl implements CategoriesService{

    private final CategoryRepo categoryRepo;

    public CategoriesServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
