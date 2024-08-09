    private WeightedPath dijkstra() {
        return dijkstra.compute(id("a"), id("g"))
                .orElseThrow(() -> new AssertionError("No path"));
    }