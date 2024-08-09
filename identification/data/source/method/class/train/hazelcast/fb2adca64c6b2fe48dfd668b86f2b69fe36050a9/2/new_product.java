public MapStoreConfig setClassName(@Nonnull String className) {
        this.className = checkHasText(className, "Map store class name must contain text");
        this.implementation = null;
        return this;
    }