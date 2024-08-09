public boolean remove(T object) {
    synchronized (mLock) {
      boolean success = mObjects.remove(object);
      if (success) {
        removeFromIndices(object);
      }
      return success;
    }
  }