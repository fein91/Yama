package yamascanner.rest;

public class YamaUrlBuilder {
    private final String domain;
    private final String resource;
    private Long resourceId;
    private String resourceClarification;
    private String resourceFilterParams;
    private String output = "json";

    public YamaUrlBuilder(String domain, Resource resource) {
        this.domain = domain;
        this.resource = resource.toString();
    }

    public YamaUrlBuilder resourceId(long resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public YamaUrlBuilder resourceClarification(ResourceClarification resourceClarification) {
        this.resourceClarification = resourceClarification.toString();
        return this;
    }

    public YamaUrlBuilder resourceFilterParams(String resourceFilterParams) {
        this.resourceFilterParams = resourceFilterParams;
        return this;
    }

    public String build() {
        StringBuilder strBuilder = new StringBuilder(domain)
                .append("/").append(resource);
        if (resourceClarification == null) {
            strBuilder.append('.').append(output);
        }
        if (resourceId != null) {
            strBuilder.append("/").append(resourceId);
        }
        if (resourceClarification != null) {
            strBuilder.append("/").append(resourceClarification);
            strBuilder.append('.').append(output);
        }
        if (resourceFilterParams != null) {
            strBuilder.append(resourceFilterParams);
        }
        return strBuilder.toString();
    }

    public enum Resource {
        CATEGORY("category"),
        MODEL("model");

        Resource(String resource) {
            this.resource = resource;
        }

        String resource;

        @Override
        public String toString() {
            return resource;
        }
    }

    public enum ResourceClarification {
        CHILDREN("children");

        ResourceClarification(String resourceClarification) {
            this.resourceClarification = resourceClarification;
        }

        String resourceClarification;

        @Override
        public String toString() {
            return resourceClarification;
        }
    }

}
