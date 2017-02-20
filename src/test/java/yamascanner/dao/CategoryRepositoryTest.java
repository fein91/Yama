package yamascanner.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import yamascanner.rest.dto.Category;
import yamascanner.rest.dto.CategoryBuilder;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void testFindAll() throws Exception {
        List<Category> result = categoryRepository.findAll();
        Assert.notEmpty(result);
        System.out.println(result);
    }

    @Test
    public void testFindById() throws Exception {
        Category result = categoryRepository.findById(1);
        Assert.notNull(result);
        System.out.println(result);
    }

    @Test
    public void testCreate() throws Exception {
        Category category = new CategoryBuilder(11)
                .childrenCount(5)
                .name("inserted category")
                .parentId(1)
                .build();

        categoryRepository.create(category);
    }

    @Test
    public void testCreateBatch() throws Exception {
        Category category1 = new CategoryBuilder(1111)
                .childrenCount(5)
                .name("batch inserted category1")
                .parentId(1)
                .build();

        Category category2 = new CategoryBuilder(1112)
                .childrenCount(5)
                .name("batch inserted category2")
                .parentId(1113)
                .build();

        Category category3 = new CategoryBuilder(1113)
                .childrenCount(5)
                .name("batch inserted category3")
                .parentId(1)
                .build();

        categoryRepository.createBatch(Arrays.asList(category1, category2, category3));
    }
}
