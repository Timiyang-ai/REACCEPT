    @Test
    @SuppressWarnings("unchecked")
    public void resetReadersToCheckpoint() {
        Map<Segment, Long> positions = new HashMap<>();
        IntStream.of(2).forEach(segNum -> positions.put(new Segment(SCOPE, "s1", segNum), 10L));
        Checkpoint checkpoint = new CheckpointImpl("testChkPoint", positions);
        readerGroup.resetReaderGroup(ReaderGroupConfig.builder().startFromCheckpoint(checkpoint).build());
        verify(synchronizer, times(1)).updateStateUnconditionally(any(Update.class));
    }