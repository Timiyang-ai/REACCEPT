public RingbufferConfig setCapacity(int capacity) {
        checkPositive(capacity, "capacity can't be smaller than 1");
        this.capacity = capacity;
        return this;
    }