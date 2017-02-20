package yamascanner.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import yamascanner.rest.dto.Categories;
import yamascanner.rest.dto.CategoriesHolder;
import yamascanner.rest.dto.Category;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

@Service
public class YamaApiClient {

    private final static Logger LOGGER = Logger.getLogger("yamascanner.rest.YamaApiClient");
    private final static int CATEGORIES_COUNT_MAX = 30;

    //private String domainUrl = "http://localhost:8080";
    private String domainUrl = "https://api.content.market.yandex.ru/v1";

    public Categories getTopCategoriesPage(int page) {
        String getTopCategoriesUrl = new YamaUrlBuilder(domainUrl, YamaUrlBuilder.Resource.CATEGORY)
                .resourceFilterParams("?count=" + CATEGORIES_COUNT_MAX + "&page=" + page + "&geo_id=187")
                .build();

        LOGGER.info("get top categories page url: [" + getTopCategoriesUrl+"]");

        Categories topCategories = makeCall(getTopCategoriesUrl);

        LOGGER.info("got top categories page: " + topCategories);
        return topCategories;
    }

    protected Categories makeCall(String url) {
        final HttpHeaders headers = createHttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (RestClientException e){
            //process exception
            if(e instanceof HttpStatusCodeException){
                String errorResponse=((HttpStatusCodeException)e).getResponseBodyAsString();
                LOGGER.severe("error response: " + errorResponse);
            }
            throw e;
        }
        LOGGER.info(responseEntity.toString());

        ObjectMapper mapper = new ObjectMapper();
        try {
           return mapper.readValue(responseEntity.getBody(), CategoriesHolder.class).getCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpHeaders createHttpHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.set("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.0; it-IT; rv:1.7.12) Gecko/20050915");
        headers.add("Host", "api.content.market.yandex.ru");
        headers.add("Authorization","8Tz5Niuuz8QJNzOkpgYtDAhGiuCwU9");
        return headers;
    }

    public Categories getChildCategoriesPage(Category category, int page) {
        String getChildCategoriesUrl = new YamaUrlBuilder(domainUrl, YamaUrlBuilder.Resource.CATEGORY)
                .resourceId(category.getId())
                .resourceClarification(YamaUrlBuilder.ResourceClarification.CHILDREN)
                .resourceFilterParams("?count=" + CATEGORIES_COUNT_MAX + "&page=" + page + "&geo_id=187")
                .build();

        LOGGER.info("get child categories page url: " + getChildCategoriesUrl);
        Categories childCategories = makeCall(getChildCategoriesUrl);
        LOGGER.info("got child categories page: " + childCategories);

        return childCategories;
    }

    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }
}
