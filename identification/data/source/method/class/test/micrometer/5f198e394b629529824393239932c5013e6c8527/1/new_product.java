Optional<String> writeLongTaskTimer(LongTaskTimer timer) {
        return Optional.of(INDEX_LINE + writeDocument(timer, builder -> {
            builder.append(",\"activeTasks\":").append(timer.activeTasks());
            builder.append(",\"duration\":").append(timer.duration(getBaseTimeUnit()));
        }));
    }