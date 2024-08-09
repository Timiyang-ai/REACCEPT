@Override
    @Deprecated
    public void resetReadersToCheckpoint(Checkpoint checkpoint) {
        synchronizer.updateState((state, updates) -> {
            ReaderGroupConfig config = state.getConfig();
            Map<Segment, Long> positions = new HashMap<>();
            for (StreamCut cut : checkpoint.asImpl().getPositions().values()) {
                positions.putAll(cut.asImpl().getPositions());
            }
            updates.add(new ReaderGroupStateInit(config, positions, getEndSegmentsForStreams(config)));
        });
    }