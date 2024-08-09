public MapStoreConfig setFactoryImplementation(@Nonnull Object factoryImplementation) {
        this.factoryImplementation = checkNotNull(factoryImplementation, "Map store factory cannot be null!");
        this.factoryClassName = null;
        return this;
    }