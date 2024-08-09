@Task(name = "scaleStream")
    public CompletableFuture<List<Segment>> scale(String scope, String stream, ArrayList<Integer> sealedSegments, ArrayList<AbstractMap.SimpleEntry<Double, Double>> newRanges, long scaleTimestamp) {
        Serializable[] params = {scope, stream, sealedSegments, newRanges, scaleTimestamp};
        return this.wrapper(scope, stream, Arrays.asList(params), () -> scaleBody(scope, stream, sealedSegments, newRanges, scaleTimestamp));
    }