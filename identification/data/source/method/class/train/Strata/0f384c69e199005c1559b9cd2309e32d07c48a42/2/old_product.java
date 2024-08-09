@Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 93090393:  // array
          return array;
        case 3506649:  // rows
          return rows;
        case 949721053:  // columns
          return columns;
        case -8339209:  // elements
          return elements;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }