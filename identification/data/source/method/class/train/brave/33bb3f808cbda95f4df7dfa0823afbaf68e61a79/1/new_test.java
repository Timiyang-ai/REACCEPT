@Test
    public void setServerSend_lessThanMicrosRoundUp() {
        Span finished = new Span().setName("foo").setTimestamp(1000L); // set in start span
        finished.startTick = 500L; // set in start span
        when(mockServerSpan.getSpan()).thenReturn(finished);
        when(mockServerSpanState.getCurrentServerSpan()).thenReturn(mockServerSpan);

        PowerMockito.when(System.nanoTime()).thenReturn(1000L);

        serverTracer.setServerSend();

        verify(mockSpanCollector).collect(finished);
        verifyNoMoreInteractions(mockSpanCollector);

        assertEquals(1L, finished.getDuration().longValue());
    }