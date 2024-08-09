  @Test public void build_redundantIgnored() {
    Span.Builder builder = Span.newBuilder().traceId("a");
    List<Span> trace = asList(builder.id("a").build(), builder.id("b").build(), builder.build());

    SpanNode tree = new SpanNode.Builder(logger).build(trace);
    assertThat(tree.span).isEqualTo(trace.get(0));
    assertThat(tree.children()).extracting(SpanNode::span).containsExactly(trace.get(1));
  }