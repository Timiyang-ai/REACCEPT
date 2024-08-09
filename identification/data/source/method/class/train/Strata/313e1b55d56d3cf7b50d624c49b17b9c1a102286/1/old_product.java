@Override
  public T getSingleValue() {
    if (isSingleValue()) {
      return value.getValue(0);
    }
    throw new IllegalStateException("This box does not contain a single value");
  }