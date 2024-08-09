@Override
    @Deprecated
    public void resetReadersToCheckpoint(Checkpoint checkpoint) {
        synchronizer.updateState((state, updates) -> {
            ReaderGroupConfig config = state.getConfig();
            Map<SegmentWithRange, Long> positions = new HashMap<>();
            for (StreamCut cut : checkpoint.asImpl().getPositions().values()) {
                for (Entry<Segment, Long> e : cut.asImpl().getPositions().entrySet()) {
                    //TODO watermarking: deal with empty range information.
                    positions.put(new SegmentWithRange(e.getKey(), null), e.getValue());
                }
            }
            updates.add(new ReaderGroupStateInit(config, positions, getEndSegmentsForStreams(config)));
        });
    }