public static HttpUrl parse(String url) {
    Builder builder = new Builder();
    Builder.ParseResult result = builder.parse(null, url);
    return result == Builder.ParseResult.SUCCESS ? builder.build() : null;
  }