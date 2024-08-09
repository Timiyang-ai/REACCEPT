public void clear() {
    synchronized (monitor) {
      typeCache.clear();
    }
  }