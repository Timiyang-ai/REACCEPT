public MapStoreConfig setProperties(Properties properties) {
        this.properties = isNotNull(properties, "properties");
        return this;
    }