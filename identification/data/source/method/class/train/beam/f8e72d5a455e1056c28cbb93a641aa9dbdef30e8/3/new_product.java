public static <T extends Serializable> SerializableCoder<T> of(Class<T> clazz) {
    return new SerializableCoder<>(clazz, TypeDescriptor.of(clazz));
  }