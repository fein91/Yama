package yamascanner.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {
    private int childrenCount;
    private long id;
    private String link;
    private int modelsNum;
    private String name;
    private int offersNum;
    private long parentId;
    private String type;
    private String uniqName;
    private boolean visual;

    public Category() {
    }

    /**
     *
     * @param childrenCount
     * @param id
     * @param link
     * @param modelsNum
     * @param name
     * @param offersNum
     * @param parentId
     * @param type
     * @param uniqName
     * @param visual
     */
    public Category(int childrenCount, long id, String link, int modelsNum, String name, int offersNum, long parentId, String type, String uniqName, boolean visual) {
        this.childrenCount = childrenCount;
        this.id = id;
        this.link = link;
        this.modelsNum = modelsNum;
        this.name = name;
        this.offersNum = offersNum;
        this.parentId = parentId;
        this.type = type;
        this.uniqName = uniqName;
        this.visual = visual;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getModelsNum() {
        return modelsNum;
    }

    public void setModelsNum(int modelsNum) {
        this.modelsNum = modelsNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOffersNum() {
        return offersNum;
    }

    public void setOffersNum(int offersNum) {
        this.offersNum = offersNum;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUniqName() {
        return uniqName;
    }

    public void setUniqName(String uniqName) {
        this.uniqName = uniqName;
    }

    public boolean isVisual() {
        return visual;
    }

    public void setVisual(boolean visual) {
        this.visual = visual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return id == category.id;

    }

    @Override
    public int hashCode() {
        int result = childrenCount;
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (int) (modelsNum ^ (modelsNum >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (offersNum ^ (offersNum >>> 32));
        result = 31 * result + (int) (parentId ^ (parentId >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (uniqName != null ? uniqName.hashCode() : 0);
        result = 31 * result + (visual ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "childrenCount=" + childrenCount +
                ", id=" + id +
                ", link='" + link + '\'' +
                ", modelsNum=" + modelsNum +
                ", name='" + name + '\'' +
                ", offersNum=" + offersNum +
                ", parentId=" + parentId +
                ", type='" + type + '\'' +
                ", uniqName='" + uniqName + '\'' +
                ", visual=" + visual +
                '}';
    }
}
