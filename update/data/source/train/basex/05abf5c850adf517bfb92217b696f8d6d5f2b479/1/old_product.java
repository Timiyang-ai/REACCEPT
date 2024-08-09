protected static String head(final String query) throws IOException {
    return request(query, HEAD);
  }