public String getField(String header) {
    Integer index = findIndex(header);
    if (index == null) {
      throw new IllegalArgumentException("Header not found: '" + header + "'");
    }
    return field(index);
  }