private Stream<String> writeTimer(FunctionTimer timer, long wallTime) {
        return Stream.of(index(timer, wallTime)
                .field("count", timer.count())
                .field("sum", timer.totalTime(getBaseTimeUnit()))
                .field("mean", timer.mean(getBaseTimeUnit()))
                .build());
    }