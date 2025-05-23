@Test
    public void finishSpan_userSuppliedTimestamp() {
        Span finished = new Span().setTimestamp(1000L); // Set by user
        state.setCurrentLocalSpan(finished);

        PowerMockito.when(System.currentTimeMillis()).thenReturn(2L);

        localTracer.finishSpan();

        verify(mockCollector).collect(finished);
        verifyNoMoreInteractions(mockCollector);

        assertEquals(1000L, finished.duration);
    }