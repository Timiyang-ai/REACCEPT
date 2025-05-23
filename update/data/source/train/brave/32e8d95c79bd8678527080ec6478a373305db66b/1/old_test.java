@Test
    public void startNewSpan() {
        state.setCurrentServerSpan(ServerSpan.create(PARENT_TRACE_ID, PARENT_SPAN_ID, null, "name"));

        PowerMockito.when(System.currentTimeMillis()).thenReturn(1L);
        PowerMockito.when(System.nanoTime()).thenReturn(500L);

        SpanId expectedSpanId = SpanId.create(PARENT_TRACE_ID, 555l, PARENT_SPAN_ID);

        assertEquals(expectedSpanId, localTracer.startNewSpan(COMPONENT_NAME, OPERATION_NAME));

        Span started = state.getCurrentLocalSpan();

        assertEquals(1000L, started.timestamp);
        assertEquals(500L, started.startTick.longValue());
        assertEquals("lc", started.binary_annotations.get(0).getKey());
        assertEquals(COMPONENT_NAME, new String(started.binary_annotations.get(0).getValue(), Util.UTF_8));
        assertEquals(state.getClientEndpoint(), started.binary_annotations.get(0).host);
        assertEquals(OPERATION_NAME, started.getName());
    }