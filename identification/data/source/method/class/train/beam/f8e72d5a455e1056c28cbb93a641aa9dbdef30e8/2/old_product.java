public static <T extends Serializable> SerializableCoder<T> of(Class<T> type) {
    return new SerializableCoder<>(type);
  }