package yamascanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yamascanner.rest.YamaApiClient;
import yamascanner.rest.dto.Categories;
import yamascanner.rest.dto.Category;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class CategoryTreeTraversal {

    private final static Logger LOGGER = Logger.getLogger("yamascanner.service.CategoryTreeTraversal");

    @Autowired
    YamaApiClient yamaApiClient;

    public Set<Category> readCategoriesTree() throws InterruptedException {
        Set<Category> topCategories = readTopCategories();
        Set<Category> result = new HashSet<>(topCategories);

        for (Category category : topCategories) {
            result.addAll(readCategoriesBranch(category));
        }

        return result;
    }

    public Set<Category> readTopCategories() throws InterruptedException {
        Set<Category> result = new HashSet<>();

        int page = 1;
        int total = 1;//to start first search
        int count = 0;
        while (total - count > 0) {
            Categories nextCategoriesPage = yamaApiClient.getTopCategoriesPage(page++);
            result.addAll(nextCategoriesPage.getItems());
            count += nextCategoriesPage.getCount();
            total = nextCategoriesPage.getTotal();

            Thread.sleep(300); //to ensure market api won't reject
        }

        return result;
    }

    public Set<Category> readCategoriesBranch(Category parentCategory) throws InterruptedException {
        if (parentCategory.getChildrenCount() <= 0) {
            return Collections.emptySet();
        }
        LOGGER.info("reading branch for category: [" + parentCategory.getId()+"]");

        Set<Category> result = new HashSet<>();
        for (Category category : readChildCategories(parentCategory)) {
            result.add(category);
            result.addAll(readCategoriesBranch(category));
        }

        LOGGER.info("branch read for category: ["+ parentCategory.getId()+"], result: " + result);
        return result;
    }

    public Set<Category> readChildCategories(Category category) throws InterruptedException {
        Set<Category> result = new HashSet<>();

        int page = 1;
        int total = 1;//to start first search
        int count = 0;
        while (total - count > 0) {
            Categories nextCategoriesPage = yamaApiClient.getChildCategoriesPage(category, page++);
            result.addAll(nextCategoriesPage.getItems());
            count += nextCategoriesPage.getCount();
            total = nextCategoriesPage.getTotal();

            Thread.sleep(300);//to ensure market api won't reject
        }

        return result;
    }

    //TODO temp hack
    public void setDomainUrl(String domainUrl) {
        this.yamaApiClient.setDomainUrl(domainUrl);
    }

}
