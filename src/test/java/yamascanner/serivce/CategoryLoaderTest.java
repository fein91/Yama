package yamascanner.serivce;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import yamascanner.service.CategoryLoader;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryLoaderTest {

    @Autowired
    CategoryLoader categoryLoader;

    @Test
    @Ignore
    public void loadTop() {
        categoryLoader.loadTopCategories();
    }

    @Test
    public void loadBranch() {
        //categoryLoader.loadCategoryBranch(7877999L);
        //categoryLoader.loadCategoryBranch(90722L);
        //categoryLoader.loadCategoryBranch(90801L);
        //categoryLoader.loadCategoryBranch(198119L);
        //categoryLoader.loadCategoryBranch(90509L);//failed
        //categoryLoader.loadCategoryBranch(90402L);
//        categoryLoader.loadCategoryBranch(91009L);
        //categoryLoader.loadCategoryBranch(91735L);
        categoryLoader.loadCategoryBranch(90666L);//failed





    }
}
