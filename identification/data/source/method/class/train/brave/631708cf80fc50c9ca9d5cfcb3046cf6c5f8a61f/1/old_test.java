@Test
    public void setServerSend_lessThanMicrosRoundUp() {
        span.setTimestamp(START_TIME_MICROSECONDS);
        ServerSpan serverSpan = new AutoValue_ServerSpan(span.context(), span, true);
        serverTracer.spanAndEndpoint().state().setCurrentServerSpan(serverSpan);

        PowerMockito.when(System.nanoTime()).thenReturn(50L);

        serverTracer.setServerSend();

        verify(mockSpanCollector).collect(span);
        verifyNoMoreInteractions(mockSpanCollector);

        assertEquals(1L, span.getDuration().longValue());
    }