    @Test
    void newDecorator_shouldWorkWhenRequestContextCurrentTraceContextNotConfigured() {
        BraveClient.newDecorator(HttpTracing.create(Tracing.newBuilder().build()));
    }