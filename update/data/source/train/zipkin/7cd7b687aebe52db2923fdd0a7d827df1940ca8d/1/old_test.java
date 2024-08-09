@Test
  public void getTraces_binaryAnnotationWithEmptyValue() {
    Span span = Span.builder()
        .traceId(1)
        .name("call1")
        .id(1)
        .timestamp((today + 1) * 1000)
        .addBinaryAnnotation(BinaryAnnotation.create("empty", "", ep)).build();

    accept(span);

    assertThat(store().getTraces((QueryRequest.builder().serviceName("service").build())))
        .containsExactly(asList(span));

    assertThat(store().getTrace(1L))
        .containsExactly(span);
  }