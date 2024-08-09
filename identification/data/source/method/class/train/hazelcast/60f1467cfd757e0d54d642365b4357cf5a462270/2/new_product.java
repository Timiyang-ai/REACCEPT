public MapStoreConfig setFactoryClassName(@Nonnull String factoryClassName) {
        this.factoryClassName = checkHasText(factoryClassName, "Map store factory class name must contain text");
        this.factoryImplementation = null;
        return this;
    }