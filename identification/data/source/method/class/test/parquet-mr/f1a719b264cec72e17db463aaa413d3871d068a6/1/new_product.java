public URI qualifiedURI(String filename) throws IOException {
    try {
      URI fileURI = new URI(filename);
      if (RESOURCE_URI_SCHEME.equals(fileURI.getScheme())) {
        return fileURI;
      }
    } catch (URISyntaxException ignore) {}
    return qualifiedPath(filename).toUri();
  }