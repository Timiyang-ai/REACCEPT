@Task(name = "scaleStream")
    public CompletableFuture<List<Segment>> scale(String scope, String stream, List<Integer> sealedSegments, List<AbstractMap.SimpleEntry<Double, Double>> newRanges, long scaleTimestamp) {
        throw new NotImplementedException();
    }