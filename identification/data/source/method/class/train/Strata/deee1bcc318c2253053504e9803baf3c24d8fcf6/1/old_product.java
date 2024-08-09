public String getField(String header) {
    return findField(header)
        .orElseThrow(() -> new IllegalArgumentException("Header not found: " + header));
  }