private Stream<String> writeCounter(FunctionCounter counter, long wallTime) {
        return Stream.of(index(counter, wallTime).field("count", counter.count()).build());
    }