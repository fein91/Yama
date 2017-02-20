package yamascanner.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Categories {
    private int count;
    private int page;
    private int total;
    private List<Category> items = new ArrayList<>();

    public Categories() {
    }

    public Categories(int count, int page, int total, List<Category> items) {
        this.count = count;
        this.page = page;
        this.total = total;
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Category> getItems() {
        return items;
    }

    public void setItems(List<Category> items) {
        this.items = items;
    }

    public void addItems(List<Category> items) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.addAll(items);
    }

    @Override
    public String toString() {
        return "Categories{" +
                "count=" + count +
                ", page=" + page +
                ", total=" + total +
                ", items=" + items +
                '}';
    }
}
