public String getValue(String header) {
    String value = getField(header);
    if (value.isEmpty()) {
      throw new IllegalArgumentException("No value was found for field: '" + header + "'");
    } else {
      return value;
    }
  }