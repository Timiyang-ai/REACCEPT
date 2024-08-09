@Override
  public void put(Class<?> rootType, Object id, Object bean) {
    synchronized (monitor) {
      getClassContext(rootType).put(id, bean);
    }
  }