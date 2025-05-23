@Test
    public void finishSpan_lessThanMicrosRoundUp() {
        Span finished = new Span().setName("foo").setTimestamp(1000L); // set in start span
        finished.startTick = 500L; // set in start span
        state.setCurrentLocalSpan(finished);

        PowerMockito.when(System.nanoTime()).thenReturn(1000L);

        localTracer.finishSpan();

        verify(mockReporter).report(finished.toZipkin());
        verifyNoMoreInteractions(mockReporter);

        assertEquals(1L, finished.getDuration().longValue());
    }