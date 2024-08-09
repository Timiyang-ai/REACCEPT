@Override
  public T getSingleValue() {
    throw new IllegalStateException("This box does not contain a single value");
  }