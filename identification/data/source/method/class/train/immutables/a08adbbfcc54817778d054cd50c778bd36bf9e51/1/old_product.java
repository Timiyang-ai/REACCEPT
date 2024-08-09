default String name(Class<?> entityClass) {
    return name(EntityContext.of(entityClass));
  }