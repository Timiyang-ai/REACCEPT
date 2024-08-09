public static List<Header> createRequestHeaders(String operationName, byte[][] headers) {
    List<Header> okhttpHeaders = Lists.newArrayListWithCapacity(6);
    okhttpHeaders.add(new Header(Header.TARGET_PATH, operationName));
    okhttpHeaders.add(SCHEME_HEADER);
    okhttpHeaders.add(CONTENT_TYPE_HEADER);
    for (int i = 0; i < headers.length; i++) {
      okhttpHeaders.add(new Header(ByteString.of(headers[i]), ByteString.of(headers[++i])));
    }
    return okhttpHeaders;
  }