@Test
    public void finishSpan_userSuppliedTimestamp() {
        Span finished = new Span().setTimestamp(1000L); // Set by user

        when(mockState.getCurrentClientSpan()).thenReturn(finished);
        when(mockClientTracer.currentTimeMicroseconds()).thenReturn(1500L);

        localTracer.finishSpan();

        verify(mockState, times(2)).getCurrentClientSpan(); // 2 times, since Span.duration is derived
        verify(mockClientTracer).currentTimeMicroseconds();
        verify(mockSpanCollector).collect(finished);
        verify(mockState).setCurrentClientServiceName(null);
        verify(mockState).setCurrentClientSpan(null);
        verifyNoMoreInteractions(mockClientTracer, mockState, mockClientTracer);

        assertEquals(500L, finished.duration);
    }