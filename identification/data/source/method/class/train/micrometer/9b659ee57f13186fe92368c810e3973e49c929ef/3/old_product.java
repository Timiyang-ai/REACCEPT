private Stream<String> writeCounter(Counter counter, long wallTime) {
        return Stream.of(index(counter, wallTime).field("count", counter.count()).build());
    }