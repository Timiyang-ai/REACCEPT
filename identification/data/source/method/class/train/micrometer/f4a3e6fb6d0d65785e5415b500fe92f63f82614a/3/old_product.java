Stream<String> writeFunctionCounter(FunctionCounter counter) {
        return Stream.of(writeMetric(counter.getId(), config().clock().wallTime(), counter.count()));
    }