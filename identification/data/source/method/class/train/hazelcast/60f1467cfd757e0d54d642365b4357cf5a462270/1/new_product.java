public MapStoreConfig setImplementation(@Nonnull Object implementation) {
        this.implementation = checkNotNull(implementation, "Map store cannot be null!");
        this.className = null;
        return this;
    }