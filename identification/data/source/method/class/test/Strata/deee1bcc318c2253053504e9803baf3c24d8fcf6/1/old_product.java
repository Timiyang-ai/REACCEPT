public String getField(Pattern headerPattern) {
    return findField(headerPattern)
        .orElseThrow(() -> new IllegalArgumentException("Header pattern not found: '" + headerPattern + "'"));
  }