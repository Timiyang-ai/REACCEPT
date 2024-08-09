public static Element serialize(Object object) throws XmlSerializationException {
    return serialize(object, TRUE_FILTER);
  }