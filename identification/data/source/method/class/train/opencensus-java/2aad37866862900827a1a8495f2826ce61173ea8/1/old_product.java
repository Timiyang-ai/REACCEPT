public String toLowerBase16() {
    return BaseEncoding.base16().lowerCase().encode(bytes);
  }