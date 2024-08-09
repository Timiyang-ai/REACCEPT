public static List<Header> createRequestHeaders(Metadata.Headers headers, String defaultPath,
      String defaultAuthority) {
    Preconditions.checkNotNull(headers, "headers");
    Preconditions.checkNotNull(defaultPath, "defaultPath");
    Preconditions.checkNotNull(defaultAuthority, "defaultAuthority");

    List<Header> okhttpHeaders = Lists.newArrayListWithCapacity(6);

    // Set GRPC-specific headers.
    okhttpHeaders.add(SCHEME_HEADER);
    okhttpHeaders.add(METHOD_HEADER);
    String authority = headers.getAuthority() != null ? headers.getAuthority() : defaultAuthority;
    okhttpHeaders.add(new Header(Header.TARGET_AUTHORITY, authority));
    String path = headers.getPath() != null ? headers.getPath() : defaultPath;
    okhttpHeaders.add(new Header(Header.TARGET_PATH, path));

    // All non-pseudo headers must come after pseudo headers.
    okhttpHeaders.add(CONTENT_TYPE_HEADER);

    // Now add any application-provided headers.
    byte[][] serializedHeaders = headers.serialize();
    for (int i = 0; i < serializedHeaders.length; i++) {
      ByteString key = ByteString.of(serializedHeaders[i]);
      ByteString value = ByteString.of(serializedHeaders[++i]);
      if (isApplicationHeader(key)) {
        okhttpHeaders.add(new Header(key, value));
      }
    }

    return okhttpHeaders;
  }