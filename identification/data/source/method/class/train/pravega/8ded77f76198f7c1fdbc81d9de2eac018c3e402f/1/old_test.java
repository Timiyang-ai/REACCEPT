    @Test
    public void getRanking() throws Exception {
        assertTrue(readerState.getOnlineReaders().isEmpty());
        AddReader addR1 = new ReaderGroupState.AddReader("r1");
        addR1.applyTo(readerState, revision);
        assertEquals(1, readerState.getOnlineReaders().size());
        AddReader addR2 = new ReaderGroupState.AddReader("r2");
        addR2.applyTo(readerState, revision);
        assertEquals(2, readerState.getOnlineReaders().size());
        new ReaderGroupState.AcquireSegment("r1", getSegment("S1")).applyTo(readerState, revision);
        SegmentWithRange s1r = new SegmentWithRange(getSegment("S1"), 0, 1);
        ImmutableMap<SegmentWithRange, Long> positions = ImmutableMap.of(s1r, 123L);
        new ReaderGroupState.UpdateDistanceToTail("r1", 1, positions).applyTo(readerState, revision);
        assertEquals(Collections.singleton(getSegment("S1")), readerState.getSegments("r1"));
        assertEquals(0, readerState.getRanking("r1"));
        assertEquals(1, readerState.getRanking("r2"));
        assertEquals(123L, readerState.getLastReadPositions(getStream("S1")).get(s1r).longValue());
        new ReaderGroupState.AcquireSegment("r1", getSegment("S2")).applyTo(readerState, revision);
        assertEquals(2, readerState.getSegments("r1").size());
        assertEquals(0, readerState.getRanking("r1"));
        assertEquals(1, readerState.getRanking("r2"));
        new ReaderGroupState.ReleaseSegment("r1", getSegment("S1"), 1).applyTo(readerState, revision);
        new ReaderGroupState.ReleaseSegment("r1", getSegment("S2"), 1).applyTo(readerState, revision);
        new ReaderGroupState.AcquireSegment("r2", getSegment("S1")).applyTo(readerState, revision);
        new ReaderGroupState.AcquireSegment("r2", getSegment("S2")).applyTo(readerState, revision);
        SegmentWithRange s2r = new SegmentWithRange(getSegment("S2"), 0, 1);
        positions = ImmutableMap.of(s2r, 123L);
        new ReaderGroupState.UpdateDistanceToTail("r2", 1, positions).applyTo(readerState, revision);
        assertEquals(0, readerState.getSegments("r1").size());
        assertEquals(1, readerState.getRanking("r1"));
        assertEquals(0, readerState.getRanking("r2"));
        assertEquals(1L, readerState.getLastReadPositions(getStream("S1")).get(s1r).longValue());
        assertEquals(123L, readerState.getLastReadPositions(getStream("S2")).get(s2r).longValue());
    }