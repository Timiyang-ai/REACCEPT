public static XContentBuilder marshall(Application bean) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        marshallInto(bean, builder);
        return builder;
    }