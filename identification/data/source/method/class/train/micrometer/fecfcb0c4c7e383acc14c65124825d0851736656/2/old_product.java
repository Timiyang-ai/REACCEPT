String writeFunctionCounter(FunctionCounter counter) {
            return writeEvent(counter, event("count", counter.count()));
        }