  private MediaType parse(String string) {
    return useGet
        ? MediaType.get(string)
        : MediaType.parse(string);
  }