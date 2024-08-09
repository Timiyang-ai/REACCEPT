public RingbufferConfig setCapacity(int capacity) {
        this.capacity = checkPositive(capacity, "capacity can't be smaller than 1");
        return this;
    }