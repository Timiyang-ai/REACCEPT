public static Element serialize(@NotNull Object object) throws XmlSerializationException {
    return serialize(object, TRUE_FILTER);
  }