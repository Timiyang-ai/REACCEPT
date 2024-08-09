public static <T extends Serializable> SerializableCoder<T> of(TypeDescriptor<T> type) {
    @SuppressWarnings("unchecked")
    Class<T> clazz = (Class<T>) type.getRawType();
    return new SerializableCoder<>(clazz, type);
  }