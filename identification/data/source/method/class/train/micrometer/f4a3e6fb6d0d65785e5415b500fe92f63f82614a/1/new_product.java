@Nullable
    Optional<String> writeFunctionCounter(FunctionCounter counter) {
        double count = counter.count();
        if (Double.isFinite(count) && count > 0) {
            // can't use "count" field because sum is required whenever count is set.
            return Optional.of(write(counter.getId(), "functionCounter", Fields.Value.tag(), decimal(count)));
        }
        return Optional.empty();
    }