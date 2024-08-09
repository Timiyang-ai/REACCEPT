public T getValue() {
    if (valueOrException instanceof ExceptionWrapper) { return null; }
    return valueOrException;
  }