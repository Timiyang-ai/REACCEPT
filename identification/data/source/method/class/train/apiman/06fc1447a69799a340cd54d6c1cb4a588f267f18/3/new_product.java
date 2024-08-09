public static XContentBuilder marshall(Client bean) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        marshallInto(bean, builder);
        return builder;
    }