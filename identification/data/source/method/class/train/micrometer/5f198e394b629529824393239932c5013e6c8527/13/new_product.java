Optional<String> writeFunctionTimer(FunctionTimer timer) {
        return Optional.of(INDEX_LINE + writeDocument(timer, builder -> {
            builder.append(",\"count\":").append(timer.count());
            builder.append(",\"sum\" :").append(timer.totalTime(getBaseTimeUnit()));
            builder.append(",\"mean\":").append(timer.mean(getBaseTimeUnit()));
        }));
    }