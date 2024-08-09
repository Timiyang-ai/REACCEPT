@Test
    public void startNewSpan() {
        brave.serverSpanThreadBinder().setCurrentSpan(ServerSpan.create(PARENT_CONTEXT, "name"));

        PowerMockito.when(System.nanoTime()).thenReturn(500L);

        SpanId newContext = brave.localTracer().startNewSpan(COMPONENT_NAME, OPERATION_NAME);
        assertThat(newContext).isEqualTo(
            PARENT_CONTEXT.toBuilder()
                .parentId(PARENT_CONTEXT.spanId)
                .spanId(newContext.spanId)
                .build()
        );

        Span started = brave.localSpanThreadBinder().getCurrentLocalSpan();

        assertEquals(START_TIME_MICROSECONDS, started.getTimestamp().longValue());
        assertEquals("lc", started.getBinary_annotations().get(0).getKey());
        assertEquals(COMPONENT_NAME, new String(started.getBinary_annotations().get(0).getValue(), Util.UTF_8));
        assertThat(started.getBinary_annotations().get(0).host).isEqualTo(endpoint);
        assertEquals(OPERATION_NAME, started.getName());
    }