  private static Element serialize(Object bean, SerializationFilter filter) {
    return XmlSerializer.serialize(bean, filter);
  }