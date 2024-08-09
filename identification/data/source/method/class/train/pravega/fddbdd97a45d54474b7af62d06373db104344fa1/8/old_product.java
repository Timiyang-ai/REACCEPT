@Task(name = "scaleStream")
    public CompletableFuture<List<Segment>> scale(String scope, String stream, List<Integer> sealedSegments, List<AbstractMap.SimpleEntry<Double, Double>> newRanges, long scaleTimestamp) {
        Object[] params = {scope, stream, sealedSegments, newRanges, scaleTimestamp};
        return this.wrapper(scope, stream, Arrays.asList(params), () -> scaleBody(scope, stream, sealedSegments, newRanges, scaleTimestamp));
    }