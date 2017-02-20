package yamascanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yamascanner.dao.CategoryRepository;
import yamascanner.model.CategoryTree;
import yamascanner.rest.dto.Category;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public CategoryTree buildCategoryTree() {
        List<Category> categories = categoryRepository.findAll();
        CategoryTree holder = new CategoryTree();
        for (Category category : categories) {
            if (category.getParentId() == 90401) {
                CategoryTree childCategory = new CategoryTree(category);
                holder.getChildren().add(childCategory);
            }
        }
        throw new UnsupportedOperationException();
    }
}
