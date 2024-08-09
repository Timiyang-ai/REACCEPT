@Test
    public void finishSpan_lessThanMicrosRoundUp() {
        Span finished = new Span().setName("foo").setTimestamp(START_TIME_MICROSECONDS);
        state.setCurrentLocalSpan(finished);

        PowerMockito.when(System.nanoTime()).thenReturn(50L);

        localTracer.finishSpan();

        verify(mockReporter).report(finished.toZipkin());
        verifyNoMoreInteractions(mockReporter);

        assertEquals(1L, finished.getDuration().longValue());
    }