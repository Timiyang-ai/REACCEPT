Optional<String> writeFunctionCounter(FunctionCounter counter) {
        return Optional.of(writeDocument(counter, builder -> {
            builder.append(",\"count\":").append(counter.count());
        }));
    }