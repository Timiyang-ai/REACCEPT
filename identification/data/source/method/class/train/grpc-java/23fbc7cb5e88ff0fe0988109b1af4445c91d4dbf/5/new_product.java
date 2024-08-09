public static List<Header> createRequestHeaders(Metadata headers, String defaultPath,
      String authority, String userAgent) {
    Preconditions.checkNotNull(headers, "headers");
    Preconditions.checkNotNull(defaultPath, "defaultPath");
    Preconditions.checkNotNull(authority, "authority");

    // 7 is the number of explicit add calls below.
    List<Header> okhttpHeaders = new ArrayList<Header>(7 + headers.headerCount());

    // Set GRPC-specific headers.
    okhttpHeaders.add(SCHEME_HEADER);
    okhttpHeaders.add(METHOD_HEADER);

    okhttpHeaders.add(new Header(Header.TARGET_AUTHORITY, authority));
    String path = defaultPath;
    okhttpHeaders.add(new Header(Header.TARGET_PATH, path));

    okhttpHeaders.add(new Header(GrpcUtil.USER_AGENT_KEY.name(), userAgent));

    // All non-pseudo headers must come after pseudo headers.
    okhttpHeaders.add(CONTENT_TYPE_HEADER);
    okhttpHeaders.add(TE_HEADER);

    // Now add any application-provided headers.
    byte[][] serializedHeaders = TransportFrameUtil.toHttp2Headers(headers);
    for (int i = 0; i < serializedHeaders.length; i += 2) {
      ByteString key = ByteString.of(serializedHeaders[i]);
      String keyString = key.utf8();
      if (isApplicationHeader(keyString)) {
        ByteString value = ByteString.of(serializedHeaders[i + 1]);
        okhttpHeaders.add(new Header(key, value));
      }
    }

    return okhttpHeaders;
  }