public void put(Object id, Object bean) {
    synchronized (monitor) {
      getClassContext(bean.getClass()).put(id, bean);
    }
  }