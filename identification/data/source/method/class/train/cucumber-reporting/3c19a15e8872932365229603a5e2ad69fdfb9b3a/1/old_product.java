public int size() {
        int size = 0;
        for (Status status : Status.values()) {
            size += counter.get(status).intValue();
        }
        return size;
    }