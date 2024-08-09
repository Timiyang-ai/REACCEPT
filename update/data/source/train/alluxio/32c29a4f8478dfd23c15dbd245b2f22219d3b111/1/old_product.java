public PropertyKey format(int ordinal) {
    return PropertyKey.fromString(String.format(mFormat, ordinal));
  }