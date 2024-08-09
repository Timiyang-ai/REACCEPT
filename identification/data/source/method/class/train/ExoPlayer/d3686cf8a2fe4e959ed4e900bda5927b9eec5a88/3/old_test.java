  @Test
  public void withUri_copiesHttpRequestHeaders() {
    Map<String, String> httpRequestProperties = createRequestProperties(5);
    DataSpec dataSpec = createDataSpecWithHeaders(httpRequestProperties);

    DataSpec dataSpecCopy = dataSpec.withUri(Uri.parse("www.new-uri.com"));

    assertThat(dataSpecCopy.httpRequestHeaders).isEqualTo(httpRequestProperties);
  }