  @Test
  public void withStreamTracerFactory() {
    CallOptions opts1 = CallOptions.DEFAULT.withStreamTracerFactory(tracerFactory1);
    CallOptions opts2 = opts1.withStreamTracerFactory(tracerFactory2);
    CallOptions opts3 = opts2.withStreamTracerFactory(tracerFactory2);

    assertThat(opts1.getStreamTracerFactories()).containsExactly(tracerFactory1);
    assertThat(opts2.getStreamTracerFactories()).containsExactly(tracerFactory1, tracerFactory2)
        .inOrder();
    assertThat(opts3.getStreamTracerFactories())
        .containsExactly(tracerFactory1, tracerFactory2, tracerFactory2).inOrder();

    try {
      CallOptions.DEFAULT.getStreamTracerFactories().add(tracerFactory1);
      fail("Should have thrown. The list should be unmodifiable.");
    } catch (UnsupportedOperationException e) {
      // Expected
    }

    try {
      opts2.getStreamTracerFactories().clear();
      fail("Should have thrown. The list should be unmodifiable.");
    } catch (UnsupportedOperationException e) {
      // Expected
    }
  }