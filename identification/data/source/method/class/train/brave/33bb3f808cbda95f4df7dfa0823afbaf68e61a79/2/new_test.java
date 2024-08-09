@Test
    public void setClientReceived_lessThanMicrosRoundUp() {
        Span finished = new Span().setName("foo").setTimestamp(1000L); // set in start span
        finished.startTick = 500L; // set in start span
        state.setCurrentClientSpan(finished);

        PowerMockito.when(System.nanoTime()).thenReturn(1000L);

        clientTracer.setClientReceived();

        verify(mockCollector).collect(finished);
        verifyNoMoreInteractions(mockCollector);

        assertEquals(1L, finished.getDuration().longValue());
    }