Stream<String> writeFunctionCounter(FunctionCounter counter) {
        double count = counter.count();
        if (Double.isFinite(count)) {
            return Stream.of(writeMetric(counter.getId(), config().clock().wallTime(), count));
        }
        return Stream.empty();
    }