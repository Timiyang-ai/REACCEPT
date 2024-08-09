public boolean remove(T object) {
    synchronized (object) {
      if (mObjects.contains(object)) {
        removeFromIndices(object);
        return mObjects.remove(object);
      } else {
        return false;
      }
    }
  }