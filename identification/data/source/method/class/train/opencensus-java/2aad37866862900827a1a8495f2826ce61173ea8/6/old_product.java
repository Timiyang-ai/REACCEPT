public String toLowerBase16() {
    StringBuilder stringBuilder = new StringBuilder(BASE16_SIZE);
    BigendianEncoding.longToBase16String(id, stringBuilder);
    return stringBuilder.toString();
  }