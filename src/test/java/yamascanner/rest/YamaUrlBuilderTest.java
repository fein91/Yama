package yamascanner.rest;

import org.junit.Assert;
import org.junit.Test;

public class YamaUrlBuilderTest {

    @Test
    public void testTopCategoriesUrl() {
        String url = new YamaUrlBuilder("https://api.content.market.yandex.ru/v1", YamaUrlBuilder.Resource.CATEGORY)
                .resourceFilterParams("?count=30&page=1&geo_id=187")
                .build();
        Assert.assertEquals("https://api.content.market.yandex.ru/v1/category.json?count=30&page=1&geo_id=187", url);
    }

    @Test
    public void testChildCategoriesUrl() {
        String getChildCategoriesUrl = new YamaUrlBuilder("https://api.content.market.yandex.ru/v1", YamaUrlBuilder.Resource.CATEGORY)
                .resourceId(1234L)
                .resourceClarification(YamaUrlBuilder.ResourceClarification.CHILDREN)
                .resourceFilterParams("?count=30&page=1&geo_id=187")
                .build();
        Assert.assertEquals("https://api.content.market.yandex.ru/v1/category/1234/children.json?count=30&page=1&geo_id=187", getChildCategoriesUrl);
    }
}
