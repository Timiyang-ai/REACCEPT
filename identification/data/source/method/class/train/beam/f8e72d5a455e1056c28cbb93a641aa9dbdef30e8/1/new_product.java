public static <T extends Serializable> SerializableCoder<T> of(Class<T> clazz) {
    checkEqualsMethodDefined(clazz);
    return new SerializableCoder<>(clazz, TypeDescriptor.of(clazz));
  }