  @Test
  public <T extends Serializable> void testSerializableCoderIsSerializableWithGenericTypeToken()
      throws Exception {
    SerializableCoder<T> coder = SerializableCoder.of(new TypeDescriptor<T>() {});
    CoderProperties.coderSerializable(coder);
  }