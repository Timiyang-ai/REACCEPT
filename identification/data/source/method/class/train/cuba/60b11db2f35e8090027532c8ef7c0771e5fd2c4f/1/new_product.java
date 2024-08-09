@Override
    public View getView(Class<? extends Entity> entityClass, String name) {
        return getView(metadata.getClassNN(entityClass), name);
    }