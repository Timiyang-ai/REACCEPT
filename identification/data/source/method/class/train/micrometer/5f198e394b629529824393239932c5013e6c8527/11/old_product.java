private Stream<String> writeTimer(Timer timer, long wallTime) {
        Stream.Builder<String> stream = Stream.builder();
        stream.add(index(timer, wallTime)
                .field("count", timer.count())
                .field("sum", timer.totalTime(getBaseTimeUnit()))
                .field("mean", timer.mean(getBaseTimeUnit()))
                .field("max", timer.max(getBaseTimeUnit()))
                .build());

        return stream.build();
    }