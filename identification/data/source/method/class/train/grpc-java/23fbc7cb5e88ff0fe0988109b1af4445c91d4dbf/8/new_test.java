  @Test
  public void createRequestHeaders_sanitizes() {
    Metadata metaData = new Metadata();

    // Intentionally being explicit here rather than relying on any pre-defined lists of headers,
    // since the goal of this test is to validate the correctness of such lists in the first place.
    metaData.put(GrpcUtil.CONTENT_TYPE_KEY, "to-be-removed");
    metaData.put(GrpcUtil.USER_AGENT_KEY, "to-be-removed");
    metaData.put(GrpcUtil.TE_HEADER, "to-be-removed");


    Metadata.Key<String> userKey = Metadata.Key.of("user-key", Metadata.ASCII_STRING_MARSHALLER);
    String userValue = "user-value";
    metaData.put(userKey, userValue);

    String path = "//testServerice/test";
    String authority = "localhost";
    String userAgent = "useragent";

    List<Header> headers = Headers.createRequestHeaders(
        metaData,
        path,
        authority,
        userAgent,
        false,
        false);

    // 7 reserved headers, 1 user header
    assertEquals(7 + 1, headers.size());
    // Check the 3 reserved headers that are non pseudo
    // Users can not create pseudo headers keys so no need to check for them here
    assertThat(headers).contains(Headers.CONTENT_TYPE_HEADER);
    assertThat(headers).contains(new Header(GrpcUtil.USER_AGENT_KEY.name(), userAgent));
    assertThat(headers).contains(new Header(GrpcUtil.TE_HEADER.name(), GrpcUtil.TE_TRAILERS));
    // Check the user header is in tact
    assertThat(headers).contains(new Header(userKey.name(), userValue));
  }