Stream<SignalFxProtocolBuffers.DataPoint.Builder> addLongTaskTimer(LongTaskTimer longTaskTimer) {
        return Stream.of(
                addDatapoint(longTaskTimer, GAUGE, "activeTasks", longTaskTimer.activeTasks()),
                addDatapoint(longTaskTimer, COUNTER, "duration", longTaskTimer.duration(getBaseTimeUnit()))
        );
    }