    private Path calcPath(GraphHopperStorage ghStorage, int from, int to) {
        return calcPath(ghStorage, defaultWeighting, from, to);
    }