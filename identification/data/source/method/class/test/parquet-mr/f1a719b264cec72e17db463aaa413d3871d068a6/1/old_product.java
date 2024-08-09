public URI qualifiedURI(String filename) throws IOException {
    URI fileURI = URI.create(filename);
    if (RESOURCE_URI_SCHEME.equals(fileURI.getScheme())) {
      return fileURI;
    } else {
      return qualifiedPath(filename).toUri();
    }
  }