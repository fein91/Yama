package yamascanner.model;

import yamascanner.rest.dto.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryTree {
    Category category;
    List<CategoryTree> children = new ArrayList<>();

    public CategoryTree() {
    }

    public CategoryTree(Category category) {
        this.category = category;
    }

    public List<CategoryTree> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryTree> children) {
        this.children = children;
    }

    public Category getCategory() {
        return category;
    }
}
