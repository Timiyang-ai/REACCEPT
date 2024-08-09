@Test
  public void getTraces_lookback() {
    accept(span1, span3); // span1's timestamp is 1000, span3's timestamp is 2000

    assertThat(
        store().getTraces(new QueryRequest.Builder("service").endTs(today + 1L).lookback(1L).build()))
        .containsExactly(asList(span1));
    assertThat(
        store().getTraces(new QueryRequest.Builder("service").endTs(today + 2L).lookback(1L).build()))
        .containsExactly(asList(span3), asList(span1));
    assertThat(
        store().getTraces(new QueryRequest.Builder("service").endTs(today + 3L).lookback(1L).build()))
        .containsExactly(asList(span3));
    assertThat(
        store().getTraces(new QueryRequest.Builder("service").endTs(today + 3L).lookback(2L).build()))
        .containsExactly(asList(span3), asList(span1));
  }