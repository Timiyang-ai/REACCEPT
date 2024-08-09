public PropertyKey format(Object params) {
    return PropertyKey.fromString(String.format(mFormat, params));
  }