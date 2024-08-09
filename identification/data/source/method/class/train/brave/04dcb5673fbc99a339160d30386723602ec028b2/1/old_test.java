  @Test public void annotate() {
    spanCustomizer.annotate("foo");
    span.flush();

    assertThat(spans).flatExtracting(zipkin2.Span::annotations)
      .extracting(Annotation::value)
      .containsExactly("foo");
  }