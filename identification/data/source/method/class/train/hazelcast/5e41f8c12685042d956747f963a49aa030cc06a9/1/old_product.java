public MapStoreConfig setProperties(Properties properties) {
        ValidationUtil.isNotNull(properties, "properties");
        this.properties = properties;
        return this;
    }