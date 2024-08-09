public boolean remove(T object) {
    // Locking this object protects against removing the exact object that might be in the
    // process of being added, but does not protect against removing a distinct, but equivalent
    // object.
    synchronized (object) {
      if (mObjects.contains(object)) {
        removeFromIndices(object);
        return mObjects.remove(object);
      } else {
        return false;
      }
    }
  }