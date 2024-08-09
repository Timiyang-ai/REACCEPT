private Stream<String> writeLongTaskTimer(LongTaskTimer timer, long wallTime) {
        return Stream.of(index(timer, wallTime)
                .field("activeTasks", timer.activeTasks())
                .field("duration", timer.duration(getBaseTimeUnit()))
                .build());
    }