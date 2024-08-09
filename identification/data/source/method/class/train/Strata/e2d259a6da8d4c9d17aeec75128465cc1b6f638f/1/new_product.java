@Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3530753:  // size
          return size;
        case -823812830:  // values
          return values;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }