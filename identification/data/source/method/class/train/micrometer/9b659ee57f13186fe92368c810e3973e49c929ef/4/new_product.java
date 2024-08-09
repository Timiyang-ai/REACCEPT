Optional<String> writeFunctionCounter(FunctionCounter counter) {
        return Optional.of(INDEX_LINE + writeDocument(counter, builder -> {
            builder.append(",\"count\":").append(counter.count());
        }));
    }