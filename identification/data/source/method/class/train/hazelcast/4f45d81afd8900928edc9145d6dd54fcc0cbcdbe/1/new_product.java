public long remainingCapacity() {
        if (expirationPolicy != null) {
            return ringbuffer.getCapacity() - size();
        }

        return ringbuffer.getCapacity();
    }