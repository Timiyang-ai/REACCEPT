@Task(name = "scaleStream", version = "1.0")
    public CompletableFuture<List<Segment>> scale(String scope, String stream, ArrayList<Integer> sealedSegments, ArrayList<AbstractMap.SimpleEntry<Double, Double>> newRanges, long scaleTimestamp) {
        Serializable[] params = {scope, stream, sealedSegments, newRanges, scaleTimestamp};
        return execute(
                String.format(Paths.STREAM_LOCKS, scope, stream),
                String.format(Paths.STREAM_TASKS, scope, stream),
                new Serializable[]{scope, stream, sealedSegments, newRanges, scaleTimestamp},
                () -> scaleBody(scope, stream, sealedSegments, newRanges, scaleTimestamp));
    }