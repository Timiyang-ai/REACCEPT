@Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 93090393:  // array
          return array;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }