@Override
    public View getView(Class<? extends Entity> entityClass, String name) {
        return getView(metadata.getSession().getClassNN(entityClass), name);
    }