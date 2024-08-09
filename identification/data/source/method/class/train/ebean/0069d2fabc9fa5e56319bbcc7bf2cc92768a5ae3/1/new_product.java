private static ExtraDdl read(String resourcePath) {

    try (InputStream is = ExtraDdlXmlReader.class.getResourceAsStream(resourcePath)) {
      if (is == null) {
        // we expect this and check for null
        return null;
      }
      return read(is);
    } catch (IOException e) {
      throw new IllegalStateException("Error on auto close of " + resourcePath, e);
    }
  }