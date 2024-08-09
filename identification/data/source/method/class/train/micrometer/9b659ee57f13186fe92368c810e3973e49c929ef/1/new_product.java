Optional<String> writeCounter(Counter counter) {
        return Optional.of(writeDocument(counter, builder -> {
            builder.append(",\"count\":").append(counter.count());
        }));
    }