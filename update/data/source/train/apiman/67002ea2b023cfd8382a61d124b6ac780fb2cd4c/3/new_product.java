@SuppressWarnings("nls")
    public static XContentBuilder marshall(ApiContract bean) throws Exception {
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
        builder.field("apiKey", bean.getApikey());
        builder.field("plan", bean.getPlan());
        builder.field("application");
        marshallInto(bean.getApplication(), builder);
        builder.field("api");
        marshallInto(bean.getApi(), builder);
        List<Policy> policies = bean.getPolicies();
        if (policies != null) {
            builder.startArray("policies");
            for (Policy policy : policies) {
                builder.startObject()
                    .field("policyImpl", policy.getPolicyImpl())
                    .field("policyJsonConfig", policy.getPolicyJsonConfig())
                .endObject();
            }
            builder.endArray();
        }
        builder.endObject();
        return builder;
    }