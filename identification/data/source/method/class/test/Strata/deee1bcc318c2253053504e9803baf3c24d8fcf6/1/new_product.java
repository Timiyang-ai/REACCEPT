public String getField(Pattern headerPattern) {
    for (int i = 0; i < headers.size(); i++) {
      if (headerPattern.matcher(headers.get(i)).matches()) {
        return field(i);
      }
    }
    throw new IllegalArgumentException("Header pattern not found: '" + headerPattern + "'");
  }