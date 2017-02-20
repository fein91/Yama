package yamascanner.rest.dto;

/**
 * Created by fein on 2/17/2017.
 */
public class CategoryBuilder {
    private final long id;
    private long parentId;
    private int childrenCount;
    private String name;
    private String uniqName;
    private String link;
    private int modelsNum;
    private int offersNum;
    private String type;
    private boolean visual;


    public CategoryBuilder(long id) {
        this.id = id;
    }

    public CategoryBuilder parentId(long parentId) {
        this.parentId = parentId;
        return this;
    }

    public CategoryBuilder childrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
        return this;
    }

    public CategoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder uniqName(String uniqName) {
        this.uniqName = uniqName;
        return this;
    }

    public CategoryBuilder modelsNum(int modelsNum) {
        this.modelsNum = modelsNum;
        return this;
    }

    public CategoryBuilder offersNum(int offersNum) {
        this.offersNum = offersNum;
        return this;
    }

    public CategoryBuilder type(String type) {
        this.type = type;
        return this;
    }

    public CategoryBuilder visual(boolean visual) {
        this.visual = visual;
        return this;
    }

    public Category build() {
        return new Category(childrenCount, id, link, modelsNum, name, offersNum, parentId, type, uniqName, visual);
    }
}
