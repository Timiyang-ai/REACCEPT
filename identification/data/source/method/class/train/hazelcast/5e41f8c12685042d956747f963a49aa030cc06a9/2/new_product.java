public MapStoreConfig setProperties(Properties properties) {
        this.properties = checkNotNull(properties, "Map store config properties cannot be null!");
        return this;
    }