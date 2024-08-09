@Override
    public void putProperty(AbstractProperty<?> prop) {
        if (prop == null) {
            throw new IllegalArgumentException("ff4j-core: Cannot insert null property into cache");
        }
        if (prop.getName() == null || prop.getName().isEmpty()) {
            throw new IllegalArgumentException("ff4j-core: Cannot insert property with null identifier into cache");
        }
        getPropertyCache().put(prop.getName(), new InMemoryCacheEntry<AbstractProperty<?>>(prop));
    }