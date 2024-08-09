Optional<String> writeCounter(Counter counter) {
        return Optional.of(INDEX_LINE + writeDocument(counter, builder -> {
            builder.append(",\"count\":").append(counter.count());
        }));
    }