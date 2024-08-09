@Test
    public void startNewSpan() {
        state.setCurrentServerSpan(ServerSpan.create(PARENT_CONTEXT, "name"));

        PowerMockito.when(System.nanoTime()).thenReturn(500L);

        SpanId expectedContext = PARENT_CONTEXT.toBuilder().spanId(555L).parentId(PARENT_CONTEXT.spanId).build();

        assertEquals(expectedContext, localTracer.startNewSpan(COMPONENT_NAME, OPERATION_NAME));

        Span started = state.getCurrentLocalSpan();

        assertEquals(START_TIME_MICROSECONDS, started.getTimestamp().longValue());
        assertEquals("lc", started.getBinary_annotations().get(0).getKey());
        assertEquals(COMPONENT_NAME, new String(started.getBinary_annotations().get(0).getValue(), Util.UTF_8));
        assertEquals(state.endpoint(), started.getBinary_annotations().get(0).host);
        assertEquals(OPERATION_NAME, started.getName());
    }