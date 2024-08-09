@Override
  public boolean remove(Object object) {
    // Locking this object protects against removing the exact object that might be in the
    // process of being added, but does not protect against removing a distinct, but equivalent
    // object.
    synchronized (object) {
      if (mObjects.contains(object)) {
        // This isn't technically typesafe. However, given that success is true, it's very unlikely
        // that the object passed to remove is not of type <T>.
        @SuppressWarnings("unchecked")
        T tObj = (T) object;
        removeFromIndices(tObj);
        return mObjects.remove(tObj);
      } else {
        return false;
      }
    }
  }