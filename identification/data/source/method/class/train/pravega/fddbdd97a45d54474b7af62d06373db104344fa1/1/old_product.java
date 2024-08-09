@Task(name = "scaleStream", version = "1.0", resource = "{scope}/{stream}")
    public CompletableFuture<List<Segment>> scale(String scope, String stream, ArrayList<Integer> sealedSegments, ArrayList<AbstractMap.SimpleEntry<Double, Double>> newRanges, long scaleTimestamp) {
        Serializable[] params = {scope, stream, sealedSegments, newRanges, scaleTimestamp};
        return execute(
                getResource(scope, stream),
                new Serializable[]{scope, stream, sealedSegments, newRanges, scaleTimestamp},
                () -> scaleBody(scope, stream, sealedSegments, newRanges, scaleTimestamp),
                null);
    }