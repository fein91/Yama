package yamascanner.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import yamascanner.rest.dto.Categories;
import yamascanner.rest.dto.Category;

import java.util.ArrayList;
import java.util.List;

@RestController
public class YamaMockController {

    @RequestMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
     public Categories topCategories() {
        List<Category> categoriesList = new ArrayList<>();
        categoriesList.add(new Category(3, 111, "url", 2, "test cat1", 3, 0, "guru", "test cat1", false));
        categoriesList.add(new Category(4, 112, "url", 2, "test cat2", 3, 0, "guru", "test cat2", false));
        categoriesList.add(new Category(0, 113, "url", 2, "test cat3", 3, 0, "guru", "test cat3", false));

        Categories categories = new Categories(3, 1, 3, categoriesList);
        return categories;
    }

    @RequestMapping(value = "/category/{id}/children", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
    public Categories childCategories(@PathVariable("id") int id) {
        Categories categories = null;
        List<Category> categoriesList = new ArrayList<>();

        switch (id) {
            case 111:
                categoriesList.add(new Category(0, 114, "url", 2, "test cat4", 3, 111, "guru", "test cat4", false));
                categoriesList.add(new Category(0, 115, "url", 2, "test cat5", 3, 111, "guru", "test cat5", false));
                categoriesList.add(new Category(0, 116, "url", 2, "test cat6", 3, 111, "noguru", "test cat6", false));

                categories = new Categories(3, 1, 3, categoriesList);
                break;
            case 112:
                categoriesList.add(new Category(0, 117, "url", 2, "test cat7", 3, 112, "guru", "test cat7", false));
                categoriesList.add(new Category(0, 118, "url", 2, "test cat8", 3, 112, "noguru", "test cat8", false));
                categoriesList.add(new Category(0, 119, "url", 2, "test cat9", 3, 112, "guru", "test cat9", false));
                categoriesList.add(new Category(0, 120, "url", 2, "test cat10", 3, 112, "guru", "test cat10", false));

                categories = new Categories(4, 1, 4, categoriesList);
                break;
        }

        return categories;
    }

}
