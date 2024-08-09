public long remainingCapacity() {
        if (isTTLEnabled()) {
            return capacity - size();
        }

        return capacity;
    }