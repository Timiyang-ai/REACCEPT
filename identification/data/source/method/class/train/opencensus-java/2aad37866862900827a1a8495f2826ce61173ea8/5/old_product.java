public String toLowerBase16() {
    StringBuilder stringBuilder = new StringBuilder(BASE16_SIZE);
    BigendianEncoding.longToBase16String(idHi, stringBuilder);
    BigendianEncoding.longToBase16String(idLo, stringBuilder);
    return stringBuilder.toString();
  }