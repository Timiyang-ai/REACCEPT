public static <T> Write<T> write(Class<T> recordClass) {
    return AvroIO.<T>defaultWriteBuilder()
        .setRecordClass(recordClass)
        .setSchema(ReflectData.get().getSchema(recordClass))
        .build();
  }