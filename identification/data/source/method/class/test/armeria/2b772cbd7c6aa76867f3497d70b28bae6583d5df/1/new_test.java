    @Test
    void newDecorator_shouldFailFastWhenRequestContextCurrentTraceContextNotConfigured() {
        assertThatThrownBy(() -> BraveService.newDecorator(HttpTracing.create(Tracing.newBuilder().build())))
                .isInstanceOf(IllegalStateException.class).hasMessage(
                "Tracing.currentTraceContext is not a RequestContextCurrentTraceContext scope. Please " +
                "call Tracing.Builder.currentTraceContext(RequestContextCurrentTraceContext.ofDefault())."
        );
    }