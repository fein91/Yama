package yamascanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yamascanner.dao.CategoryRepository;
import yamascanner.rest.dto.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class CategoryLoader {

    private final static Logger LOGGER = Logger.getLogger("yamascanner.service.CategoryLoader");

    @Autowired
    CategoryTreeTraversal categoryTreeTraversal;
    @Autowired
    CategoryRepository categoryRepository;

    public Set<Category> loadTopCategories() {
        Set<Category> topCategories = null;
        try {
            topCategories = categoryTreeTraversal.readTopCategories();
        } catch (InterruptedException e) {
            LOGGER.severe("error occured while reading top categories");
            e.printStackTrace();
            return Collections.emptySet();
        }
        categoryRepository.createBatch(new ArrayList<>(topCategories));
        return topCategories;
    }

    public Set<Category> loadCategoryBranch(long categoryId) {
        Category category = categoryRepository.findById(categoryId);
        Set<Category> categories = null;
        try {
            categories = categoryTreeTraversal.readCategoriesBranch(category);
        } catch (InterruptedException e) {
            LOGGER.severe("error occured while reading branch of: " + category);
            e.printStackTrace();
            return Collections.emptySet();
        }
        categoryRepository.createBatch(new ArrayList<>(categories));
        return categories;
    }
}
