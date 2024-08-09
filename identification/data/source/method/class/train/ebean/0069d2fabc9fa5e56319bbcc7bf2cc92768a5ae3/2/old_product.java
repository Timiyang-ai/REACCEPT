public static ExtraDdl read(String resourcePath) {

    InputStream is = ExtraDdlXmlReader.class.getResourceAsStream(resourcePath);
    if (is == null) {
      // we expect this and check for null
      return null;
    }
    return read(is);
  }