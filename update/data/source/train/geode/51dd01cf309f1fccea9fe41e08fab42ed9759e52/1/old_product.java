public XmlParser getDelegate(final String namespaceUri) {
      try {
        return (XmlParser) getDelegateMethod.invoke(this, namespaceUri);
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        throw new IllegalStateException(e);
      }
    }