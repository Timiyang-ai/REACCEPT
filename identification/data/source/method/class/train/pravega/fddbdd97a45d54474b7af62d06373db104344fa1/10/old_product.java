@Task(name = "scaleStream",
            returnType = List.class,
            parametersTypes = {String.class, String.class, List.class, List.class, Long.class})
    public CompletableFuture<List<Segment>> scale(String scope, String stream, List<Integer> sealedSegments, List<AbstractMap.SimpleEntry<Double, Double>> newRanges, long scaleTimestamp) {
        throw new NotImplementedException();
    }