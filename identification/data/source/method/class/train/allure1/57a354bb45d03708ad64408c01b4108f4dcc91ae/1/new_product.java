public void put(Step step) {
        synchronized (LOCK) {
            get().add(step);
        }
    }