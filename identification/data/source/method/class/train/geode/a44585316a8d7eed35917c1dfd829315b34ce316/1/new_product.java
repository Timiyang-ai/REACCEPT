protected boolean isSerializable(final Object object) {
    if (object == null) {
      return true;
    }
    return Serializable.class.isInstance(object);
  }