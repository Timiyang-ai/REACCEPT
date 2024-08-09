public static XmlElement parseElements(ByteSource source, ToIntFunction<String> filterFn) {
    ArgChecker.notNull(source, "source");
    ArgChecker.notNull(filterFn, "filterFn");
    ToIntFunction<String> safeFilterFn = name -> Math.max(filterFn.applyAsInt(name), 0);
    return Unchecked.wrap(() -> {
      try (InputStream in = source.openBufferedStream()) {
        XMLStreamReader xmlReader = xmlInputFactory().createXMLStreamReader(in);
        try {
          return parseElements(xmlReader, safeFilterFn, Integer.MAX_VALUE);
        } finally {
          xmlReader.close();
        }
      }
    });
  }