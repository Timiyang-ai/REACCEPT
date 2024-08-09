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

        zipkin.Span started = spans.get(0);
        assertThat(started.timestamp).isEqualTo(START_TIME_MICROSECONDS);
        assertThat(started.name).isEqualTo(OPERATION_NAME);
        assertThat(started.binaryAnnotations).containsExactly(
            zipkin.BinaryAnnotation.create("lc", COMPONENT_NAME, ZIPKIN_ENDPOINT)
        );
    }