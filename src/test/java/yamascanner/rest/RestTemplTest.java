package yamascanner.rest;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import yamascanner.rest.dto.Quote;
import yamascanner.rest.dto.Category;
import yamascanner.service.CategoryTreeTraversal;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplTest {

    @Autowired
    private CategoryTreeTraversal categoryTreeTraversal;

    @LocalServerPort
    private int port;

    @Test
    public void getExtRandom() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);

        Assert.assertNotNull(quote);
    }


    @Test
    public void getMockCategories() throws Exception {
        categoryTreeTraversal.setDomainUrl("http://localhost:" + port);
        Set<Category> topCategories = categoryTreeTraversal.readCategoriesTree();
        Assert.assertNotNull(topCategories);
        System.out.println(topCategories);
    }
}