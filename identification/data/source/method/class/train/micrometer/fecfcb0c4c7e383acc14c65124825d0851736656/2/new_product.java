@Nullable
        String writeFunctionCounter(FunctionCounter counter) {
            double count = counter.count();
            if (Double.isFinite(count)) {
                return writeEvent(counter, event("count", count));
            }
            return null;
        }