  @Test
  public void subrange_copiesHttpRequestHeaders() {
    Map<String, String> httpRequestProperties = createRequestProperties(5);
    DataSpec dataSpec = createDataSpecWithHeaders(httpRequestProperties);

    DataSpec dataSpecCopy = dataSpec.subrange(2);

    assertThat(dataSpecCopy.httpRequestHeaders).isEqualTo(httpRequestProperties);
  }