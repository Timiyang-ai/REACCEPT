  @Test
  public void getTraces_storedViaPost() throws IOException {
    List<Span> trace = asList(CLIENT_SPAN);
    // write the span to the zipkin using http
    assertThat(postSpansV1(trace).code()).isEqualTo(202);

    // read the traces directly
    assertThat(zipkin.getTraces()).containsOnly(trace);
  }