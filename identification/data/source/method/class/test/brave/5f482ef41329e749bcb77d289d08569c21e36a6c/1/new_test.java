@Test
    public void startNewSpan() {
        brave.serverTracer().setStateCurrentTrace(PARENT_CONTEXT, "name");

        SpanId newContext = brave.localTracer().startNewSpan(COMPONENT_NAME, OPERATION_NAME);
        assertThat(newContext).isEqualTo(
            PARENT_CONTEXT.toBuilder()
                .parentId(PARENT_CONTEXT.spanId)
                .spanId(newContext.spanId)
                .build()
        );

        assertThat(spans).isEmpty(); // doesn't flush on start
        recorder.flush(brave.localSpanThreadBinder().get());

        zipkin2.Span started = spans.get(0);
        assertThat(started.timestamp()).isGreaterThanOrEqualTo(START_TIME_MICROSECONDS);
        assertThat(started.name()).isEqualTo(OPERATION_NAME);
        assertThat(started.tags()).containsOnly(
            entry("lc", COMPONENT_NAME)
        );
    }