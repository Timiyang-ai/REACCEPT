public static <T> Write<T> write(Class<T> recordClass) {
    return new Write<>(
        AvroIO.<T, T>defaultWriteBuilder()
            .setGenericRecords(false)
            .setSchema(ReflectData.get().getSchema(recordClass))
            .build());
  }